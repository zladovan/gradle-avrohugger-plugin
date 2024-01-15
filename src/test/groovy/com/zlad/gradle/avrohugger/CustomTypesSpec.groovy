package com.zlad.gradle.avrohugger

import avrohugger.types.AvroScalaTypes
import avrohugger.types.JavaSqlDate$
import avrohugger.types.JavaSqlTime$
import avrohugger.types.JavaSqlTimestamp$
import avrohugger.types.JavaTimeLocalDateTime$
import avrohugger.types.JavaTimeLocalTime$
import avrohugger.types.JavaTimeZonedDateTime$
import avrohugger.types.OptionShapelessCoproduct$
import avrohugger.types.ScalaADT$
import avrohugger.types.ScalaArray$
import avrohugger.types.ScalaCaseClassWithSchema$
import avrohugger.types.ScalaCaseObjectEnum$
import avrohugger.types.ScalaDouble$
import avrohugger.types.ScalaFloat$
import avrohugger.types.ScalaInt$
import avrohugger.types.ScalaLong$
import com.zlad.gradle.avrohugger.types.CustomTypes
import com.zlad.gradle.avrohugger.types.CustomTypesValues
import scala.Option
import scala.math.BigDecimal
import spock.lang.Specification


class CustomTypesSpec extends Specification implements CustomTypesValues {

    def "should return default standard avro scala types"() {
        given:
        final defaults = new CustomTypes()

        when:
        final avroScalaTypes = defaults.toAvroScalaTypes()
        
        then:
        avroScalaTypes == AvroScalaTypes.defaults()
    }

    def "should return modified int in avro scala types"() {
        given:
        final modified = new CustomTypes(
            intType: ScalaLong
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.int() == ScalaLong$.MODULE$
    }

    def "should return modified long in avro scala types"() {
        given:
        final modified = new CustomTypes(
                longType: ScalaInt
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.long() == ScalaInt$.MODULE$
    }

    def "should return modified float in avro scala types"() {
        given:
        final modified = new CustomTypes(
                floatType: ScalaDouble
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.float() == ScalaDouble$.MODULE$
    }

    def "should return modified double in avro scala types"() {
        given:
        final modified = new CustomTypes(
                doubleType: ScalaFloat
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.double() == ScalaFloat$.MODULE$
    }

    def "should return modified record in avro scala types"() {
        given:
        final modified = new CustomTypes(
                recordType: ScalaCaseClassWithSchema
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.record() == ScalaCaseClassWithSchema$.MODULE$
    }

    def "should return modified enum in avro scala types"() {
        given:
        final modified = new CustomTypes(
                enumType: ScalaCaseObjectEnum
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.enum() == ScalaCaseObjectEnum$.MODULE$
    }

    def "should return modified union in avro scala types"() {
        given:
        final modified = new CustomTypes(
                unionType: OptionShapelessCoproduct
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.union() == OptionShapelessCoproduct$.MODULE$
    }

    def "should return modified array in avro scala types"() {
        given:
        final modified = new CustomTypes(
                arrayType: ScalaArray
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.array() == ScalaArray$.MODULE$
    }

    def "should return modified protocol in avro scala types"() {
        given:
        final modified = new CustomTypes(
                protocolType: ScalaADT
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.protocol() == ScalaADT$.MODULE$
    }

    def "should return modified decimal in avro scala types"() {
        given:
        final modified = new CustomTypes(
                decimalType: ScalaBigDecimalWithPrecision
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.decimal() == new avrohugger.types.ScalaBigDecimalWithPrecision(Option.empty())
    }

    def "should return modified decimal with rounding mode in avro scala types"() {
        given:
        final modified = new CustomTypes(
                decimalType: ScalaBigDecimal_CEILING
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.decimal() == new avrohugger.types.ScalaBigDecimal(Option.apply( BigDecimal.RoundingMode$.MODULE$.CEILING()))
    }

    def "should return modified date in avro scala types"() {
        given:
        final modified = new CustomTypes(
                dateType: JavaSqlDate
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.date() == JavaSqlDate$.MODULE$
    }

    def "should return modified timestamp millis in avro scala types"() {
        given:
        final modified = new CustomTypes(
                timestampMillisType: JavaSqlInstant
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.timestampMillis() == JavaSqlTimestamp$.MODULE$
    }

    def "should return modified time millis in avro scala types"() {
        given:
        final modified = new CustomTypes(
            timeMillisType: JavaSqlTimeMillis
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.timeMillis() == JavaSqlTime$.MODULE$
    }

    def "should return modified time micros in avro scala types"() {
        given:
        final modified = new CustomTypes(
                timeMicrosType: JavaTimeLocalTime
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.timeMicros() == JavaTimeLocalTime$.MODULE$
    }

    def "should return modified timestamp micros in avro scala types"() {
        given:
        final modified = new CustomTypes(
                timestampMicrosType: JavaTimeZonedDateTime
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.timestampMicros() == JavaTimeZonedDateTime$.MODULE$
    }

    def "should return modified local timestamp millis in avro scala types"() {
        given:
        final modified = new CustomTypes(
                localTimestampMillisType: JavaLocalDateTime
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.localTimestampMillis() == JavaTimeLocalDateTime$.MODULE$
    }

    def "should return modified local timestamp micros in avro scala types"() {
        given:
        final modified = new CustomTypes(
                localTimestampMicrosType: JavaLocalDateTime
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.localTimestampMicros() == JavaTimeLocalDateTime$.MODULE$
    }

}
