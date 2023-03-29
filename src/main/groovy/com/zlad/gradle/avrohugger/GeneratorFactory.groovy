package com.zlad.gradle.avrohugger

import avrohugger.Generator
import avrohugger.types.AvroScalaTypes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scala.Some

class GeneratorFactory implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorFactory)

    ScalaSourceFormat sourceFormat
    AvroScalaTypes types
    Map<String, String> customNamespaces
    boolean restrictedFieldNumber
    String targetScalaPartialVersion

    Generator create() {
        final format = sourceFormat.toAvrohuggerSourceFormat()
        final namespaces = ScalaConversions.convert(customNamespaces)
        logger.info("""
            Creating avrohugger generator
                - format: $format
                - types: $types
                - namespaces: $namespaces
                - targetScalaPartialVersion: $targetScalaPartialVersion
        """.stripIndent())
        new Generator(
                format,
                Some.apply(types),
                namespaces,
                // there is a restriction about maximum fields count for case classes in scala 2.10 and older
                Integer.parseInt(targetScalaPartialVersion.split("\\.")[1]) < 11,
                Thread.currentThread().contextClassLoader,
                targetScalaPartialVersion
        )
    }
}
