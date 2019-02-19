package com.zlad.gradle.avrohugger

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Property
import org.gradle.util.ConfigureUtil

class AvrohuggerExtension implements CustomTypesValues {
    final ConfigurableFileCollection sourceDirectories
    final DirectoryProperty destinationDirectory
    final Property<Map<String, String>> namespaceMapping
    final Property<CustomTypes> typeMapping

    AvrohuggerExtension(Project project) {
        sourceDirectories = project.files()
        sourceDirectories.setFrom(project.files('src/main/avro'))

        destinationDirectory = project.layout.directoryProperty()
        destinationDirectory.set(project.file("${project.buildDir}/generated-src/avro"))

        typeMapping = project.objects.property(CustomTypes)
        typeMapping.set(new CustomTypes())

        namespaceMapping = project.objects.property(Map)
        namespaceMapping.set([:])
    }

    void setSourceDirectories(FileCollection files) {
        sourceDirectories.setFrom(files)
    }

    void setDestinationDirectory(File file) {
        destinationDirectory.set(file)
    }

    void setNamespaceMapping(Map<String, String> mapping) {
        namespaceMapping.set(mapping)
    }

    void typeMapping(@DelegatesTo(CustomTypes) Closure c) {
        typeMapping(ConfigureUtil.configureUsing(c))
    }
    
    void typeMapping(Action<? super CustomTypes> action) {
        action.execute(typeMapping.get())
    }

}
