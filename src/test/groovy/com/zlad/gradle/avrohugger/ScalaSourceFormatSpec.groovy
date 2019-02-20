package com.zlad.gradle.avrohugger

import avrohugger.format.Scavro$
import avrohugger.format.SpecificRecord$
import avrohugger.format.Standard$
import spock.lang.Specification

class ScalaSourceFormatSpec extends Specification {

    def "should provide standard format"() {
        ScalaSourceFormat.Standard.toAvrohuggerSourceFormat() == Standard$.MODULE$
    }

    def "should provide specific record format"() {
        ScalaSourceFormat.SpecificRecord.toAvrohuggerSourceFormat() == SpecificRecord$.MODULE$
    }

    def "should provide scavro format"() {
        ScalaSourceFormat.Scavro.toAvrohuggerSourceFormat() == Scavro$.MODULE$
    }
}
