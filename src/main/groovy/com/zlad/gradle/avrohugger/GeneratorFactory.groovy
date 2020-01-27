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

    Generator create() {
        final format = sourceFormat.toAvrohuggerSourceFormat()
        final namespaces = ScalaConversions.convert(customNamespaces)
        logger.info("""
            Creating avrohugger generator
                - format: $format
                - types: $types
                - namespaces: $namespaces
                - restricted: $restrictedFieldNumber
        """.stripIndent())
        new Generator(
                format, 
                Some.apply(types),
                namespaces,
                restrictedFieldNumber,
                Thread.currentThread().contextClassLoader
        )
    }
}
