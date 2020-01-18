package com.zlad.gradle.avrohugger

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.util.ConfigureUtil

class AvrohuggerExtension implements CustomTypesValues, SourceFormatValues {
    final ConfigurableFileCollection sourceDirectories
    final DirectoryProperty destinationDirectory
    final MapProperty<String, String> namespaceMapping
    final Property<CustomTypes> typeMapping
    final Property<ScalaSourceFormat> sourceFormat

    AvrohuggerExtension(Project project) {
        sourceDirectories = project.files()
        sourceDirectories.setFrom(project.files('src/main/avro'))

        destinationDirectory = project.objects.directoryProperty()
        destinationDirectory.set(project.file("${project.buildDir}/generated-src/avro"))

        typeMapping = project.objects.property(CustomTypes)
        typeMapping.set(new CustomTypes())

        namespaceMapping = project.objects.mapProperty(String,String)
        namespaceMapping.set([:])

        sourceFormat = project.objects.property(ScalaSourceFormat)
        sourceFormat.set(Standard)
    }

    void setSourceDirectories(FileCollection files) {
        sourceDirectories.setFrom(files)
    }

    /**
     * Enables configuring sourceDirectories with closure like this:
     *
     * <pre>
     * sourceDirectories {
     *     from 'src/main/avro'
     *     // from compile dependencies
     *     from configurations.compile.files.collect { zipTree(it) }
     * }
     * </pre>
     */
    void sourceDirectories(@DelegatesTo(ConfigurableFileCollection) Closure c) {
        sourceDirectories(ConfigureUtil.configureUsing(c))
    }

    void sourceDirectories(Action<? super ConfigurableFileCollection> action) {
        action.execute(sourceDirectories)
    }

    void setDestinationDirectory(File file) {
        destinationDirectory.set(file)
    }

    void setNamespaceMapping(Map<String, String> mapping) {
        namespaceMapping.set(mapping)
    }

    void typeMapping(@DelegatesTo(CustomTypes) Closure c) {
        typeMapping(ConfigureUtil.configureUsing(c))
    }
    
    void typeMapping(Action<? super CustomTypes> action) {
        action.execute(typeMapping.get())
    }

    void setSourceFormat(ScalaSourceFormat format) {
        sourceFormat.set(format)
    }
}
