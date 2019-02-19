package com.zlad.gradle.avrohugger.common

import groovy.transform.Immutable

@Immutable
class TestProjectConfig {
    String buildDefinition
    List<String> inputDirectories = ['src', 'main', 'avro']
    List<String> outputDirectories = ['build', 'generated-src', 'avro']
}
