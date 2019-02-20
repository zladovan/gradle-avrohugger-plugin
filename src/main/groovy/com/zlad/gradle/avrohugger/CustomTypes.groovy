package com.zlad.gradle.avrohugger

import avrohugger.types.*
import groovy.transform.EqualsAndHashCode

/**
 * Container for all avro scala custom types.
 *
 * Original avrohugger's AvroScalaTypes case class forces to define all attributes during creation.
 * We want to define only changed fields and keep defaults for rest.
 * All not null fields will be used as override for default types specified by avrohugger library.
 *
 * This is workaround for no support for scala case class copy in java / groovy.
 */
@EqualsAndHashCode
class CustomTypes {
    AvroScalaNumberType intType
    AvroScalaNumberType longType
    AvroScalaNumberType floatType
    AvroScalaNumberType doubleType
    AvroScalaBooleanType booleanType
    AvroScalaStringType stringType
    AvroScalaNullType nullType
    AvroScalaBytesType bytesType
    AvroScalaFixedType fixedType
    AvroScalaRecordType recordType
    AvroScalaEnumType enumType
    AvroScalaUnionType unionType
    AvroScalaArrayType arrayType
    AvroScalaMapType mapType
    AvroScalaProtocolType protocolType
    AvroScalaDecimalType decimalType
    AvroScalaDateType dateType
    AvroScalaTimestampMillisType timestampMillisType
    AvroUuidType uuidType

    AvroScalaTypes toAvroScalaTypes(AvroScalaTypes defaults = AvroScalaTypes.defaults()) {
        new AvroScalaTypes(
            intType             ?: defaults.int(),
            longType            ?: defaults.long(),
            floatType           ?: defaults.float(),
            doubleType          ?: defaults.double(),
            booleanType         ?: defaults.boolean(),
            stringType          ?: defaults.string(),
            nullType            ?: defaults.null(),
            bytesType           ?: defaults.bytes(),
            fixedType           ?: defaults.fixed(),
            recordType          ?: defaults.record(),
            enumType            ?: defaults.enum(),
            unionType           ?: defaults.union(),
            arrayType           ?: defaults.array(),
            mapType             ?: defaults.map(),
            protocolType        ?: defaults.protocol(),
            decimalType         ?: defaults.decimal(),
            dateType            ?: defaults.date(),
            timestampMillisType ?: defaults.timestampMillis(),
            uuidType            ?: defaults.uuid()
        )
    }
}
