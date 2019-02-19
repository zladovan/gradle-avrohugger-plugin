package com.zlad.gradle.avrohugger.common

class Resources {

    private Resources() { }

    static InputStream read(String resource) {
        Resources.getClassLoader().getResourceAsStream(resource)
    }
}
