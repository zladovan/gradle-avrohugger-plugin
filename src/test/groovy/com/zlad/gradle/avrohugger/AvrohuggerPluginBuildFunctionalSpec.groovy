package com.zlad.gradle.avrohugger

import com.zlad.gradle.avrohugger.common.Resources
import com.zlad.gradle.avrohugger.common.TestProject
import com.zlad.gradle.avrohugger.common.TestProjectConfig
import org.gradle.testkit.runner.BuildResult
import org.junit.Rule
import org.junit.rules.ExternalResource
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AvrohuggerPluginBuildFunctionalSpec extends Specification {

    @Rule
    TestProject project = new TestProject(new TestProjectConfig(
            buildDefinition: Resources.read('sample.gradle').text,
            inputDirectories: ['src-avro'],
            outputDirectories: ['src-scala']
    ))

    def "should generate and compile scala classes during build with custom config"() {
        given:
        project.inputFile('input.avsc') << Resources.read('sample.avsc')

        when:
        final result = project.build()

        then:
        buildWasSuccessfull(result)
        compiledScalaClass(project, 'com.zlad.FullName').exists()
    }

    @Rule
    TestProject projectLarge = new TestProject(new TestProjectConfig(
            buildDefinition: Resources.read('specific-record.gradle').text,
            inputDirectories: ['src-avro'],
            outputDirectories: ['src-scala']
    ))

    def "should generate and compile large scala classes with specific record during build with custom config"() {
        given:
        projectLarge.inputFile('input.avsc') << Resources.read('large.avsc')

        when:
        final result = projectLarge.build()

        then:
        buildWasSuccessfull(result)
        compiledScalaClass(projectLarge, 'com.example.analytics.event.Interaction').exists()
    }

    /*
     * Private helper methods
     */

    private static boolean buildWasSuccessfull(BuildResult result) {
        result.task(":build").outcome == SUCCESS
    }

    private static File compiledScalaClass(TestProject project, String fullClassName) {
        project.projectFile('build', 'classes', 'scala', 'main',
                fullClassName.replace('.', '/') + '.class')
    }
}
