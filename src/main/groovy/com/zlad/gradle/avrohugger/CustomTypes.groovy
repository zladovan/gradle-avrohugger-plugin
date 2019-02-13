package com.zlad.gradle.avrohugger

import avrohugger.types.*
import groovy.transform.EqualsAndHashCode

/**
 * Container for all avro scala custom types.
 *
 * Original avrohugger's AvroScalaTypes case class forces to define all attributes during creation.
 * We want to define only changed fields and keep defaults for rest.
 * There is no support for scala case class copy in java / groovy.
 */
@EqualsAndHashCode
class CustomTypes {
    AvroScalaNumberType intType = AvroScalaTypes.defaults().int()
    AvroScalaNumberType longType = AvroScalaTypes.defaults().long()
    AvroScalaNumberType floatType = AvroScalaTypes.defaults().float()
    AvroScalaNumberType doubleType = AvroScalaTypes.defaults().double()
    AvroScalaBooleanType booleanType = AvroScalaTypes.defaults().boolean()
    AvroScalaStringType stringType = AvroScalaTypes.defaults().string()
    AvroScalaNullType nullType = AvroScalaTypes.defaults().null()
    AvroScalaBytesType bytesType = AvroScalaTypes.defaults().bytes()
    AvroScalaFixedType fixedType = AvroScalaTypes.defaults().fixed()
    AvroScalaRecordType recordType = AvroScalaTypes.defaults().record()
    AvroScalaEnumType enumType = AvroScalaTypes.defaults().enum()
    AvroScalaUnionType unionType = AvroScalaTypes.defaults().union()
    AvroScalaArrayType arrayType = AvroScalaTypes.defaults().array()
    AvroScalaMapType mapType = AvroScalaTypes.defaults().map()
    AvroScalaProtocolType protocolType = AvroScalaTypes.defaults().protocol()
    AvroScalaDecimalType decimalType = AvroScalaTypes.defaults().decimal()
    AvroScalaDateType dateType = AvroScalaTypes.defaults().date()
    AvroScalaTimestampMillisType timestampMillisType = AvroScalaTypes.defaults().timestampMillis()
    AvroUuidType uuidType = AvroScalaTypes.defaults().uuid()

    AvroScalaTypes toAvroScalaTypes() {
        new AvroScalaTypes(
            intType,
            longType,
            floatType,
            doubleType,
            booleanType,
            stringType,
            nullType,
            bytesType,
            fixedType,
            recordType,
            enumType,
            unionType,
            arrayType,
            mapType,
            protocolType,
            decimalType,
            dateType,
            timestampMillisType,
            uuidType
        )
    }
}
