package com.zlad.gradle.avrohugger

import avrohugger.filesorter.AvdlFileSorter
import avrohugger.filesorter.AvscFileSorter
import org.gradle.api.file.FileTree

import static scala.collection.JavaConverters.iterableAsScalaIterable
import static scala.collection.JavaConverters.seqAsJavaList

/**
 * Access to supported input file types
 */
class FileSource {
    final FileTree source

    FileSource(FileTree source) {
        this.source = source
    }

    List<File> getAvdlFiles() {
        sortAvdl(getSourceFiles("**/*.avdl"))
    }

    List<File> getAvscFiles() {
        sortAvsc(getSourceFiles("**/*.avsc"))
    }

    Set<File> getAvroFiles() {
        getSourceFiles("**/*.avro")
    }

    Set<File> getAvprFiles() {
        getSourceFiles("**/*.avpr")
    }

    private Set<File> getSourceFiles(String pattern) {
        source.matching { include pattern }.files
    }

    private static List<File> sortAvdl(Iterable<File> files) {
        seqAsJavaList(AvdlFileSorter.sortSchemaFiles(iterableAsScalaIterable(files)))
    }

    private static List<File> sortAvsc(Iterable<File> files) {
        seqAsJavaList(AvscFileSorter.sortSchemaFiles(iterableAsScalaIterable(files)))
    }
}
