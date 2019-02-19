package com.zlad.gradle.avrohugger

import com.zlad.gradle.avrohugger.common.Resources
import com.zlad.gradle.avrohugger.common.TestProject
import com.zlad.gradle.avrohugger.common.TestProjectConfig
import org.gradle.testkit.runner.BuildResult
import org.junit.Rule
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AvrohuggerPluginGenerateFunctionalSpec extends Specification {

    @Rule
    TestProject project = new TestProject(new TestProjectConfig(
            buildDefinition: Resources.read('minimal.gradle').text
    ))

    def "should generate scala classes from avsc file"() {
        given:
        inputFile('avsc')

        when:
        final result = project.avroScalaGenerate()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avdl file"() {
        given:
        inputFile('avdl')

        when:
        final result = project.avroScalaGenerate()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }


    def "should generate scala classes from avro file"() {
        given:
        inputFile('avro')

        when:
        final result = project.avroScalaGenerate()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avpr file"() {
        given:
        inputFile('avpr')

        when:
        final result = project.avroScalaGenerate()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    /*
     * Private helper methods
     */
    private File inputFile(String extension) {
        project.inputFile( "input.$extension") << Resources.read("sample.$extension")
    }

    private static boolean avroScalaGenerateTaskWasSuccessful(BuildResult result) {
        result.task(":avroScalaGenerate").outcome == SUCCESS
    }

    private File generatedScalaFile() {
        project.outputFile('com', 'example', 'FullName.scala')
    }

}