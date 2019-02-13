package com.zlad.gradle.avrohugger

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS


class AvrohuggerPluginFunctionalSpec extends Specification {

    @Rule
    TemporaryFolder projectDir = new TemporaryFolder()

    File inputDirecotry
    File outputDirecotry

    def setup() {
        projectDir.newFile('build.gradle') << read('sample.gradle')
        inputDirecotry = projectDir.newFolder('src-avro')
        outputDirecotry = new File(projectDir.root, 'src-scala')
    }

    def "should generate scala classes from avsc file"() {
        given:
        inputFile('avsc')

        when:
        final result = avroScalaGenerateCalled()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avdl file"() {
        given:
        inputFile('avdl')

        when:
        final result = avroScalaGenerateCalled()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }


    def "should generate scala classes from avro file"() {
        given:
        inputFile('avro')

        when:
        final result = avroScalaGenerateCalled()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avpr file"() {
        given:
        inputFile('avpr')

        when:
        final result = avroScalaGenerateCalled()

        then:
        avroScalaGenerateTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    /*
     * Private helper methods
     */
    private File inputFile(String extension) {
        new File(inputDirecotry, "input.$extension") << read("sample.$extension")
    }

    private BuildResult avroScalaGenerateCalled() {
        GradleRunner.create()
            .withProjectDir(projectDir.root)
            .withArguments('avroScalaGenerate')
            .withPluginClasspath()
            .build()
    }

    private static boolean avroScalaGenerateTaskWasSuccessful(BuildResult result) {
        result.task(":avroScalaGenerate").outcome == SUCCESS
    }

    private File generatedScalaFile() {
        new File(outputDirecotry, 'com/zlad/FullName.scala')
    }

    private static InputStream read(String resource) {
        AvrohuggerPluginFunctionalSpec.getClassLoader().getResourceAsStream(resource)
    }
}
