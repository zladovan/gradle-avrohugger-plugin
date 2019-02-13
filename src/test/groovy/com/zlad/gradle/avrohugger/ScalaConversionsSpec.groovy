package com.zlad.gradle.avrohugger

import scala.Some
import spock.lang.Specification


class ScalaConversionsSpec extends Specification {

    def "should convert java to scala immutable map"() {
        given:
        def javaMap = ['one': 1, 'two': 2]

        when:
        def scalaMap = ScalaConversions.convert(javaMap)

        then:
        verifyAll(scalaMap) {
            delegate in scala.collection.immutable.Map
            get('one') == Some.apply(1)
            get('two') == Some.apply(2)
            size() == 2
        }
    }
}
