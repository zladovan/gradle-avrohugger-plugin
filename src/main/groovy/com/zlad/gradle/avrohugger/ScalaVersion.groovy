package com.zlad.gradle.avrohugger

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.util.VersionNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.Callable

/**
 * Callable returning used scala version in partial form MAJOR.MINOR (e.g. 2.11).
 *
 * Avrohugger needs it for classes generation.
 */
class ScalaVersion implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(ScalaVersion)

    private static final SCALA_VERSION_2_11 = '2.11'
    private static final SCALA_GROUP = 'org.scala-lang'
    private static final SCALA_ARTIFACT = 'scala-library'

    private final Project project

    ScalaVersion(Project project) {
        this.project = project
    }

    @Override
    String call() throws Exception {
        return findScalaVersion(project)
    }

    private static String findScalaVersion(Project project) {
        def version = findScalaVersionOnCompileClasspath(project)
        // if version not found or it's a placeholder then try to parse `scalaVersion` project property
        // this is fallback mainly for https://github.com/ADTRAN/gradle-scala-multiversion-plugin
        if (version == null || version == "%scalaVersion%") {
            version = project.properties.get("scalaVersion")?.toString()
        }
        // if version is still not found give up and pretend that version is 2.11 (no 2.10 backward compatibility)
        if (version == null) {
            logger.warn(
                "Scala library dependency was not found. Using `$SCALA_VERSION_2_11` as default fallback."
            )
            return SCALA_VERSION_2_11
        }
        def parts = version.split("\\.")
        if (parts.size() >= 2) {
    		return parts[0] + "." + parts[1]
    	} 
    	logger.warn(
            "Invalid scala library version was found: `$version`. Using `$SCALA_VERSION_2_11` as default fallback."
        )
        return SCALA_VERSION_2_11
    }

    // look for scala library in compile classpath dependencies
    private static String findScalaVersionOnCompileClasspath(Project project) {
        final Set<Dependency> dependencies = project.configurations.findByName('compileClasspath')?.allDependencies ?: []
        final scalaLibrary =  dependencies.find {
            SCALA_GROUP == it.group && SCALA_ARTIFACT == it.name
        }
        return scalaLibrary?.version
    }
}