package com.zlad.gradle.avrohugger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

/**
 * Runnable for invoking avrohugger scala sources generation from *.avsc, *.avdl, *.avpr or *.avro files.
 *
 * It's implemented as runnable to be able to call it in separate process.
 */
class Generate implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Generate)

    private final GeneratorFactory generatorFactory
    private final String destinationDir
    private final Map<String, Collection<File>> inputFiles


    /**
     * @param generatorFactory
     * @param destinationDir path to directory where source files will be created
     * @param inputFiles labeled input files collections, generation will be invoked per collection and label is used for logging
     */
    @Inject
    Generate(GeneratorFactory generatorFactory, String destinationDir, Map<String, Collection<File>> inputFiles) {
        this.generatorFactory = generatorFactory
        this.destinationDir = destinationDir
        this.inputFiles = inputFiles
    }

    @Override
    void run() {
        final generator = generatorFactory.create()
        inputFiles.forEach { label, files ->
            files.forEach {
                logger.info("Compiling $label $it to $destinationDir")
                generator.fileToFile(it, destinationDir)
            }
        }
    }

}
