package com.zlad.gradle.avrohugger

import groovy.transform.InheritConstructors
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.util.VersionNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.Callable

/**
 * Callable returning true when used scala version is below 2.11.
 *
 * Avrohugger need to know that,
 * because case classes have restricted amount of attributes in scala 2.10 and older.
 */
class ScalaVersionBelow2_11 implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(ScalaVersionBelow2_11)

    private static final SCALA_VERSION_2_11 = VersionNumber.parse('2.11.0-M1')
    private static final SCALA_GROUP = 'org.scala-lang'
    private static final SCALA_ARTIFACT = 'scala-library'

    private final Project project

    ScalaVersionBelow2_11(Project project) {
        this.project = project
    }

    @Override
    Boolean call() throws Exception {
        findScalaVersion(project) <  SCALA_VERSION_2_11
    }

    // look for scala library in compile dependencies
    private static VersionNumber findScalaVersion(Project project) {
        final Set<Dependency> dependencies = project.configurations.findByName('compile')?.allDependencies ?: []
        final scalaLibrary =  dependencies.find {
            SCALA_GROUP == it.group && SCALA_ARTIFACT == it.name
        }
        final versionString = scalaLibrary?.version
        if (versionString == null) {
            logger.warn(
                "Scala library dependency was not found. " +
                "Avrohugger plugin will use by default `$SCALA_VERSION_2_11` for check if scala version is below 2.11."
            )
            return SCALA_VERSION_2_11
        }
        VersionNumber.parse(versionString)
    }
}