package com.zlad.gradle.avrohugger

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.provider.ProviderFactory

import javax.inject.Inject

class AvrohuggerPlugin implements Plugin<Project> {
    static final EXTENSION_NAME = 'avrohugger'
    static final TASK_GENERATE_NAME = 'generateAvroScala'

    private final ProviderFactory providerFactory

    @Inject
    AvrohuggerPlugin(ProviderFactory providerFactory) {
        this.providerFactory = providerFactory
    }

    @Override
    void apply(Project project) {
        project.plugins.apply(BasePlugin)
        def extension = registerExtension(project)
        def task = reqisterGenerateTask(project, extension)
        registerGeneratedSources(project, extension)
        registerGenerateTaskBuildDependency(project, task)
        registerCleanDestinationDirectory(project, extension)
    }

    private static AvrohuggerExtension registerExtension(Project project) {
        project.extensions.create(EXTENSION_NAME, AvrohuggerExtension, project)
    }

    private Task reqisterGenerateTask(Project project, AvrohuggerExtension extension) {
        project.tasks.create(TASK_GENERATE_NAME, GenerateScalaTask) {
            group = "Source Generation"
            description = "Generate scala case classes from avro schemas, datafiles and protocols."
            source = extension.sourceDirectories
            destinationDir = extension.destinationDirectory
            customTypes = extension.typeMapping.map {
                it.toAvroScalaTypes(extension.sourceFormat.get().toAvrohuggerSourceFormat().defaultTypes())
            }
            customNamespaces = extension.namespaceMapping
            sourceFormat = extension.sourceFormat
            restrictedFieldNumber = providerFactory.provider(new ScalaVersionBelow2_11(project))
        }
    }


    private static void registerGeneratedSources(Project project, AvrohuggerExtension extension) {
        project.afterEvaluate {
            // for the minimal use case there are no sourceSets in project
            if (project.hasProperty('sourceSets')) {
                registerGeneratedSourcesUnsafe(project, extension)
            }
        }
    }

    // look for generated source directory in all source sets
    // if the directory is not in any source set yet (it was not defined in gradle script) then add it to main scala sources
    private static void registerGeneratedSourcesUnsafe(Project project, AvrohuggerExtension extension) {
        final generatedSource = extension.destinationDirectory.get().asFile
        final alreadyRegistered = project.sourceSets.collectMany { it.allSource.srcDirs }.contains(generatedSource)
        if (!alreadyRegistered) {
            project.sourceSets.matching { it.name == 'main' }.all {
                if (it.hasProperty('scala')) {
                    it.scala.srcDir extension.destinationDirectory
                }
            }
        }
    }

    private static void registerGenerateTaskBuildDependency(Project project, Task task) {
        project.afterEvaluate {
            project.tasks.findByName('compileScala')?.dependsOn(task)
        }
    }

    private static void registerCleanDestinationDirectory(Project project, AvrohuggerExtension extension) {
        project.afterEvaluate {
            project.tasks.findByName('clean')?.configure {
                delete extension.destinationDirectory
            }
        }
    }

}
