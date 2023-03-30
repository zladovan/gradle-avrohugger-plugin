package com.zlad.gradle.avrohugger

import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

/**
 * Runnable for invoking avrohugger scala sources generation from *.avsc, *.avdl, *.avpr or *.avro files.
 *
 * It's implemented as runnable to be able to call it in separate process.
 */
abstract class Generate implements WorkAction<GenerateParameters> {

    protected static final Logger logger = LoggerFactory.getLogger(Generate)

    @Override
    void execute() {
        final generator = getParameters().getGeneratorFactory().create()
        getParameters().getInputFiles().forEach { label, files ->
            files.forEach {
                def destinationDir = getParameters().getDestinationDir()
                logger.info("Compiling $label $it to $destinationDir")
                generator.fileToFile(it, destinationDir)
            }
        }
    }

}

interface GenerateParameters extends WorkParameters {
    GeneratorFactory getGeneratorFactory()
    void setGeneratorFactory(GeneratorFactory generatorFactory)
    String getDestinationDir()
    void setDestinationDir(String destinationDir)
    Map<String, Collection<File>> getInputFiles()
    void setInputFiles(Map<String, Collection<File>> inputFiles)
}