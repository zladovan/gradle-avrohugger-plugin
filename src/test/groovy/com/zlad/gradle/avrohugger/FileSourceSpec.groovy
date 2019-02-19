package com.zlad.gradle.avrohugger

import org.gradle.testfixtures.ProjectBuilder
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification


class FileSourceSpec extends Specification {

    @Shared
    TemporaryFolder sourceDirectory = new TemporaryFolder()

    @Shared
    FileSource fileSource

    def setupSpec() {
        with(sourceDirectory) {
            create()
            newFile("one.avdl")
            newFile("two.avsc").write(readSampleAvsc())
            newFile("three.avro")
            newFile("four.avpr")
        }
        fileSource = createFileSource()
    }

    def cleanupSpec() {
        sourceDirectory.delete()
    }
    
    def "should provide avdl files"() {
        when:
        final files = fileSource.avdlFiles

        then:
        files == [ file("one.avdl") ]
    }

    def "should provide avsc files"() {
        when:
        final files = fileSource.avscFiles

        then:
        files == [ file("two.avsc") ]
    }

    def "should provide avro files"() {
        when:
        final files = fileSource.avroFiles

        then:
        files == [ file("three.avro") ] as Set
    }

    def "should provide avpr files"() {
        when:
        final files = fileSource.avprFiles

        then:
        files == [ file("four.avpr") ] as Set
    }

    /*
     * Private helper methods
     */
    private File file(String name) {
        new File(sourceRoot(), name)
    }

    private FileSource createFileSource() {
        final project = ProjectBuilder.builder().build()
        new FileSource(project.fileTree(sourceRoot()))
    }

    // on mac os temp folder is under /var which is symlink to /private/var
    // need to resolve real path for comparison
    private File sourceRoot() {
        sourceDirectory.root.toPath().toRealPath().toFile()
    }

    private static String readSampleAvsc() {
        FileSourceSpec.getResourceAsStream("/sample.avsc").text
    }
}
