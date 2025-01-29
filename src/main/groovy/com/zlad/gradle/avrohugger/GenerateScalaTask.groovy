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
abstract class GenerateScalaTask extends SourceTask {

    private static final Logger logger = LoggerFactory.getLogger(GenerateScalaTask)

    @OutputDirectory
    abstract DirectoryProperty getDestinationDir();

    @Input
    abstract Property<AvroScalaTypes> getCustomTypes();

    @Input
    abstract MapProperty<String, String> getCustomNamespaces();

    @Input
    abstract Property<ScalaSourceFormat> getSourceFormat()

    @Input
    abstract public Property<String> getTargetScalaPartialVersion();

    @Inject
    abstract protected WorkerExecutor getWorkerExecutor();

    @TaskAction
    void generate() {
        logger.info("Starting avro scala classes generation.")
        final fileSource = new FileSource(source)
        final files = [
            "Avro schema"  : fileSource.getAvscFiles(),
            "Avro IDL"     : fileSource.getAvdlFiles(),
            "Avro datafile": fileSource.getAvroFiles(),
            "Avro protocol": fileSource.getAvprFiles()
        ]
        submitWork(createGeneratorFactory(), destinationDir.get().asFile.getAbsolutePath(), files, sourceFormat.get())
    }

    private GeneratorFactory createGeneratorFactory() {
        new GeneratorFactory(
            sourceFormat: sourceFormat.get(),
            types: customTypes.get(),
            customNamespaces: customNamespaces.get(),
            targetScalaPartialVersion: targetScalaPartialVersion.get()
        )
    }

    // when SpecificRecord format used there is need to run generating in separate process
    // because avrohugger is creating (temp) directory `target` which is not deleted while gradle daemon is alive
    // when run as separate process `target` is created under `~/.gradle/workers/` and it's minimally not polluting users project
    private def submitWork(GeneratorFactory generatorFactory, String destination, Map<String, Collection<File>> inputFiles, ScalaSourceFormat fmt) {
        if (!inputFiles.isEmpty()) {
            def queue = fmt == ScalaSourceFormat.Standard ? workerExecutor.noIsolation() : workerExecutor.processIsolation()
            queue.submit(Generate, { parameters ->
                parameters.setGeneratorFactory(generatorFactory)
                parameters.setDestinationDir(destination)
                parameters.setInputFiles(inputFiles)
            })
        }
    }
}
