package com.zlad.gradle.avrohugger

import avrohugger.format.SpecificRecord$
import avrohugger.format.Standard$
import avrohugger.format.abstractions.SourceFormat

/**
 * Avrohugger's SourceFormat objects are not serializable.
 * Therefor they are wrapped with enum which is used later as input to generation task (input need to be serializable because of caching).
 */
enum ScalaSourceFormat {
    Standard,
    SpecificRecord;

    SourceFormat toAvrohuggerSourceFormat() {
        switch (this) {
            case Standard:          return Standard$.MODULE$
            case SpecificRecord:    return SpecificRecord$.MODULE$
            default:                throw new RuntimeException("Unsupported scala source type: $this")
        }
    }
}