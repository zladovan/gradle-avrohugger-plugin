package com.zlad.gradle.avrohugger

import avrohugger.Generator
import avrohugger.types.AvroScalaTypes
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import scala.Some
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GenerateScalaTask extends SourceTask {

    private static final Logger logger = LoggerFactory.getLogger(GenerateScalaTask)

    @OutputDirectory
    final DirectoryProperty destinationDir = project.layout.directoryProperty()

    @Input
    final Property<AvroScalaTypes> customTypes =  project.objects.property(AvroScalaTypes)

    @Input
    final Property<Map<String, String>> customNamespaces = project.objects.property(Map)

    @Input
    final Property<ScalaSourceFormat> sourceFormat =  project.objects.property(ScalaSourceFormat)

    @Input
    final Property<Boolean> restrictedFieldNumber =  project.objects.property(Boolean)

    @TaskAction
    void generate() {
        logger.info("Starting avro scala classes generation.")
        final fileSource = new FileSource(source)
        final destination = destinationDir.get().asFile.getAbsolutePath()
        final generator = createGenerator()

        fileSource.getAvscFiles().forEach {
            logger.info("Compiling AVSC $it to $destination")
            generator.fileToFile(it, destination)
        }

        fileSource.getAvdlFiles().forEach {
            logger.info("Compiling Avro IDL $it to $destination")
            generator.fileToFile(it, destination)
        }

        fileSource.getAvroFiles().forEach {
            logger.info("Compiling Avro datafile $it to $destination")
            generator.fileToFile(it, destination)
        }

        fileSource.getAvprFiles().forEach {
            logger.info("Compiling Avro protocol $it to $destination")
            generator.fileToFile(it, destination)
        }
    }

    private Generator createGenerator() {
        final format = sourceFormat.get().toAvrohuggerSourceFormat()
        final types = customTypes.get()
        final namespaces = ScalaConversions.convert(customNamespaces.get())
        final restricted = restrictedFieldNumber.get()
        logger.info("""
            Creating avrohugger generator
                - format: $format
                - types: $types
                - namespaces: $namespaces
                - restricted: $restricted
        """.stripIndent())
        new Generator(format, Some.apply(types), namespaces, restricted)
    }

}
