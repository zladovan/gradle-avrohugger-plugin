package com.zlad.gradle.avrohugger

import avrohugger.format.Scavro$
import avrohugger.types.AvroScalaTypes
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

    def "should return default provided scala types"() {
        given:
        final defaults = new CustomTypes()

        when:
        final avroScalaTypes = defaults.toAvroScalaTypes(Scavro$.MODULE$.defaultTypes())

        then:
        avroScalaTypes == Scavro$.MODULE$.defaultTypes()
    }

    def "should return modified int in avro scala types"() {
        given:
        final modified = new CustomTypes(
            intType: ScalaLong
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.int() == ScalaLong
    }

    def "should return modified long in avro scala types"() {
        given:
        final modified = new CustomTypes(
                longType: ScalaInt
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.long() == ScalaInt
    }

    def "should return modified float in avro scala types"() {
        given:
        final modified = new CustomTypes(
                floatType: ScalaInt
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.float() == ScalaInt
    }

    def "should return modified double in avro scala types"() {
        given:
        final modified = new CustomTypes(
                doubleType: ScalaInt
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.double() == ScalaInt
    }

    def "should return modified record in avro scala types"() {
        given:
        final modified = new CustomTypes(
                recordType: ScalaCaseClassWithSchema
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.record() == ScalaCaseClassWithSchema
    }

    def "should return modified enum in avro scala types"() {
        given:
        final modified = new CustomTypes(
                enumType: ScalaCaseObjectEnum
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.enum() == ScalaCaseObjectEnum
    }

    def "should return modified union in avro scala types"() {
        given:
        final modified = new CustomTypes(
                unionType: OptionShapelessCoproduct
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.union() == OptionShapelessCoproduct
    }

    def "should return modified array in avro scala types"() {
        given:
        final modified = new CustomTypes(
                arrayType: ScalaArray
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.array() == ScalaArray
    }

    def "should return modified protocol in avro scala types"() {
        given:
        final modified = new CustomTypes(
                protocolType: ScalaADT
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.protocol() == ScalaADT
    }

    def "should return modified decimal in avro scala types"() {
        given:
        final modified = new CustomTypes(
                decimalType: ScalaBigDecimalWithPrecision
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.decimal() == ScalaBigDecimalWithPrecision
    }

    def "should return modified decimal with rounding mode in avro scala types"() {
        given:
        final modified = new CustomTypes(
                decimalType: ScalaBigDecimal_CEILING
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.decimal() == ScalaBigDecimal_CEILING
    }

    def "should return modified date in avro scala types"() {
        given:
        final modified = new CustomTypes(
                dateType: JavaSqlDate
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.date() == JavaSqlDate
    }

    def "should return modified timestamp millis in avro scala types"() {
        given:
        final modified = new CustomTypes(
                timestampMillisType: JavaSqlTimestamp
        )

        when:
        final avroScalaTypes = modified.toAvroScalaTypes()

        then:
        avroScalaTypes.timestampMillis() == JavaSqlTimestamp
    }
    
}
