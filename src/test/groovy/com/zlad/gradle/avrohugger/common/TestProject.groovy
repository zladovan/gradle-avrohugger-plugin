package com.zlad.gradle.avrohugger.common

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.rules.ExternalResource
import org.junit.rules.TemporaryFolder

class TestProject extends ExternalResource {
    private final TestProjectConfig config

    private TemporaryFolder projectDir = new TemporaryFolder()
    private File inputDirectory
    private File outputDirecotry

    TestProject(TestProjectConfig config) {
        this.config = config
    }

    @Override
    protected void before() throws Throwable {
        projectDir.before()
        projectDir.newFile('build.gradle') << config.buildDefinition
        inputDirectory = projectDir.newFolder(config.inputDirectories as String[])
        outputDirecotry = projectFile(config.outputDirectories as String[])
    }

    @Override
    protected void after() {
        projectDir.after()
    }

    File getInputDirectory() {
        if (inputDirectory == null) {
            throw new IllegalStateException("Input directory was not created. Ensure before() method was called.")
        }
        inputDirectory
    }

    File getOutputDirectory() {
        if (outputDirecotry == null) {
            throw new IllegalStateException("Output directory was not created. Ensure before() method was called.")
        }
        outputDirecotry
    }

    GradleRunner createGradleRunner() {
        GradleRunner.create().withProjectDir(projectDir.root)
    }

    BuildResult run(String... args) {
        createGradleRunner()
            .withArguments(args)
            .withPluginClasspath()
            .build()
    }

    BuildResult build() {
        run('build')
    }

    BuildResult avroScalaGenerate() {
        run('avroScalaGenerate')
    }

    File inputFile(String... path) {
        file(getInputDirectory(), path)
    }

    File outputFile(String... path) {
        file(getOutputDirectory(), path)
    }

    File projectFile(String... path) {
        file(projectDir.root, path)
    }

    private static file(File dir, String... path) {
        path.inject(dir) {root, current -> new File(root, current) }
    }
}
