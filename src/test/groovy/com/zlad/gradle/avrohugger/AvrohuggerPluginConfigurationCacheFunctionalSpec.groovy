package com.zlad.gradle.avrohugger

import com.zlad.gradle.avrohugger.common.Resources
import com.zlad.gradle.avrohugger.common.TestProject
import com.zlad.gradle.avrohugger.common.TestProjectConfig
import org.junit.Rule
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AvrohuggerPluginConfigurationCacheFunctionalSpec extends Specification {

    @Rule
    TestProject project = new TestProject(new TestProjectConfig(
        buildDefinition: Resources.read('minimal.gradle').text
    ))

    def "configuration cache works"() {
        given:
        project.inputFile('input.avsc') << Resources.read('sample.avsc')

        when:
        def result = project.run("--configuration-cache", "generateAvroScala")

        then:
        result.task(":generateAvroScala").outcome == SUCCESS
    }
}
