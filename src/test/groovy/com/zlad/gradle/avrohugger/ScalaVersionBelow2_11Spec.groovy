package com.zlad.gradle.avrohugger

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

import java.util.concurrent.Callable


class ScalaVersionBelow2_11Spec extends Specification {

    Project project = ProjectBuilder.builder().build()

    Callable<Boolean> callable = new ScalaVersionBelow2_11(project)

    def "should return false by default if scala version is not known"() {
        when:
        final isBelow2_11 = callable.call()

        then:
        ! isBelow2_11
    }

    def "should return false for first scala 2.11 version"() {
        given:
        projectWithScalaLibrary('2.11.0-M1')

        when:
        final isBelow2_11 = callable.call()

        then:
        ! isBelow2_11
    }

    def "should return true for last scala 2.10 version"() {
        given:
        projectWithScalaLibrary('2.10.7')

        when:
        final isBelow2_11 = callable.call()

        then:
        isBelow2_11
    }

    def "should resolve version above 2.11 when used with gradle-scala-multiversion-plugin"() {
        given:
        projectWithScalaMultiversionPlugin('2.12.11')

        when:
        final isBelow2_11 = callable.call()

        then:
        ! isBelow2_11
    }

    def "should resolve version below 2.11 when used with gradle-scala-multiversion-plugin"() {
        given:
        projectWithScalaMultiversionPlugin('2.10.7')

        when:
        final isBelow2_11 = callable.call()

        then:
        isBelow2_11
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
        projectWithScalaLibrary("%scalaVersion%")
        project.extensions.extraProperties.scalaVersion = version
    }

    private static String scalaLibrary(String version) {
        "org.scala-lang:scala-library:$version"
    }
    
}
