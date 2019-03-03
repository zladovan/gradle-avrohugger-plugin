package com.zlad.gradle.avrohugger

import com.zlad.gradle.avrohugger.common.Resources
import com.zlad.gradle.avrohugger.common.TestProject
import com.zlad.gradle.avrohugger.common.TestProjectConfig
import org.gradle.testkit.runner.BuildResult
import org.junit.Rule
import spock.lang.Specification

import java.nio.file.Files

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
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avdl file"() {
        given:
        inputFile('avdl')

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }


    def "should generate scala classes from avro file"() {
        given:
        inputFile('avro')

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate scala classes from avpr file"() {
        given:
        inputFile('avpr')

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should generate specific record scala classes"() {
        given:
        avscInputWithFormat('SpecificRecord')

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().text.contains('org.apache.avro.specific.SpecificRecordBase')
    }

    def "should generate scavro scala classes"() {
        given:
        avscInputWithFormat('Scavro')

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScavroFile().exists()
    }

    def "should generate classes from schema in archive"() {
        given:
        zipArchiveInput()

        when:
        final result = project.generateAvroScala()

        then:
        generateAvroScalaTaskWasSuccessful(result)
        generatedScalaFile().exists()
    }

    def "should delete destination directory on clean"() {
        given:
        final newDestinationDirectory = destinationDirectoryOutsideBuild()

        when:
        project.clean()

        then:
        ! newDestinationDirectory.exists()
    }

    /*
     * Private helper methods
     */
    private File inputFile(String extension) {
        project.inputFile( "input.$extension") << Resources.read("sample.$extension")
    }

    private static boolean generateAvroScalaTaskWasSuccessful(BuildResult result) {
        result.task(":generateAvroScala").outcome == SUCCESS
    }

    private File generatedScalaFile() {
        project.outputFile('com', 'example', 'FullName.scala')
    }

    // scavro format appends `model` to namespace
    private File generatedScavroFile() {
        project.outputFile('com', 'example', 'model', 'FullName.scala')
    }

    private void avscInputWithFormat(String format) {
        inputFile('avsc')
        project.buildFile.append("""
            avrohugger {
                sourceFormat = ${format}
            }
        """.stripIndent())
    }

    private zipArchiveInput() {
        inputFile('zip')
        project.buildFile.append("""
            avrohugger {
                sourceDirectories {
                    from zipTree('src/main/avro/input.zip')
                }
            }
        """.stripIndent())
    }

    private File destinationDirectoryOutsideBuild() {
        project.buildFile.append("""
            avrohugger {
                destinationDirectory = file('output')
            }
        """.stripIndent())
        final File newDestinationDirectory = project.projectFile('output')
        Files.createDirectories(newDestinationDirectory.toPath())
        newDestinationDirectory
    }
}
