package com.zlad.gradle.avrohugger

import avrohugger.types.AvroScalaTypes
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.IsolationMode
import org.gradle.workers.WorkerExecutor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

@CacheableTask
class GenerateScalaTask extends SourceTask {

    private static final Logger logger = LoggerFactory.getLogger(GenerateScalaTask)

    private final WorkerExecutor workerExecutor

    @OutputDirectory
    final DirectoryProperty destinationDir = project.objects.directoryProperty()

    @Input
    final Property<AvroScalaTypes> customTypes =  project.objects.property(AvroScalaTypes)

    @Input
    final MapProperty<String, String> customNamespaces = project.objects.mapProperty(String,String)

    @Input
    final Property<ScalaSourceFormat> sourceFormat =  project.objects.property(ScalaSourceFormat)

    @Input
    final Property<String> targetScalaPartialVersion =  project.objects.property(String)

    @Inject
    GenerateScalaTask(WorkerExecutor workerExecutor) {
        super()
        this.workerExecutor = workerExecutor
    }

    @TaskAction
    void generate() {
        logger.info("Starting avro scala classes generation.")
        final fileSource = new FileSource(source)
        final files = [
            "Avro schema":   fileSource.getAvscFiles(),
            "Avro IDL":      fileSource.getAvdlFiles(),
            "Avro datafile": fileSource.getAvroFiles(),
            "Avro protocol": fileSource.getAvprFiles()
        ]
        submitWork(createGeneratorFactory(), destinationDir.get().asFile.getAbsolutePath(), files)
    }

    private GeneratorFactory createGeneratorFactory() {
        new GeneratorFactory(
            sourceFormat: sourceFormat.get(),
            types: customTypes.get(),
            customNamespaces: customNamespaces.get(),
            targetScalaPartialVersion: targetScalaPartialVersion.get()
        )
    }

    // when SpecificRecord or Scavro format used there is need to run generating in separate process
    // because avrohugger is creating (temp) directory `target` which is not deleted while gradle daemon is alive
    // when run as separate process `target` is created under `~/.gradle/workers/` and it's minimally not polluting users project
    private def submitWork = { GeneratorFactory generatorFactory, String destination, Map<String, Collection<File>> inputFiles ->
        if ( ! inputFiles.isEmpty()) {
            workerExecutor.submit(Generate) { config ->
                config.setIsolationMode(sourceFormat.get() == ScalaSourceFormat.Standard ? IsolationMode.NONE : IsolationMode.PROCESS)
                config.params(generatorFactory, destination, inputFiles)
            }
        }
    }

    @Internal("needed for access from submitWork")
    WorkerExecutor getWorkerExecutor() {
        workerExecutor
    }

}
