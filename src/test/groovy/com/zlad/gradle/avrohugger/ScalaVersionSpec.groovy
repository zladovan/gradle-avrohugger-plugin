package com.zlad.gradle.avrohugger

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

import java.util.concurrent.Callable


class ScalaVersionSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    Callable<String> callable = new ScalaVersion(project)

    def "should return 2.11 by default if scala version is not known"() {
        when:
        final version = callable.call()

        then:
        version == "2.11"
    }

    def "should return 2.11 for first scala 2.11 version"() {
        given:
        projectWithScalaLibrary('2.11.0-M1')

        when:
        final version = callable.call()

        then:
        version == "2.11"
    }

    def "should return 2.10 for last scala 2.10 version"() {
        given:
        projectWithScalaLibrary('2.10.7')

        when:
        final version = callable.call()

        then:
        version == "2.10"
    }

    def "should resolve version 2.12 when used with gradle-scala-multiversion-plugin"() {
        given:
        projectWithScalaMultiversionPlugin('2.12.11')

        when:
        final version = callable.call()

        then:
        version == "2.12"
    }

    def "should resolve version 2.10 when used with gradle-scala-multiversion-plugin"() {
        given:
        projectWithScalaMultiversionPlugin('2.10.7')

        when:
        final version = callable.call()

        then:
        version == "2.10"
    }

    def "should return 2.11 as fallback if malformed version is found"() {
        given:
        projectWithScalaMultiversionPlugin('xxx')

        when:
        final version = callable.call()

        then:
        version == "2.11"
    }

    /*
     * Private helper methods
     */
    private void projectWithScalaLibrary(String version) {
        project.pluginManager.apply("java")
        project.dependencies.add('implementation', scalaLibrary(version))
    }

    // simulation of project with `gradle-scala-multiversion-plugin`, see: https://github.com/ADTRAN/gradle-scala-multiversion-plugin
    // with this plugin scala library use placeholder `%scalaVersion%` instead of version
    // and version can be found in project property `scalaVersion`
    private  void projectWithScalaMultiversionPlugin(String version) {
        project.extensions.extraProperties.scalaVersion = version
        projectWithScalaLibrary("%scalaVersion%")
    }

    private static String scalaLibrary(String version) {
        "org.scala-lang:scala-library:$version"
    }
    
}
