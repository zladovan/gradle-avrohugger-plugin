package com.zlad.gradle.avrohugger.types

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
    NumberType intType
    NumberType longType
    NumberType floatType
    NumberType doubleType
    BoolType booleanType
    StringType stringType
    NullType nullType
    BytesType bytesType
    FixedType fixedType
    RecordType recordType
    EnumType enumType
    UnionType unionType
    ArrayType arrayType
    MapType mapType
    ProtocolType protocolType
    DecimalType decimalType
    DateType dateType
    TimestampType timestampMillisType
    UuidType uuidType

    AvroScalaTypes toAvroScalaTypes(AvroScalaTypes defaults = AvroScalaTypes.defaults()) {
        new AvroScalaTypes(
            intType?.toScalaType()             ?: defaults.int(),
            longType?.toScalaType()            ?: defaults.long(),
            floatType?.toScalaType()           ?: defaults.float(),
            doubleType?.toScalaType()          ?: defaults.double(),
            booleanType?.toScalaType()         ?: defaults.boolean(),
            stringType?.toScalaType()          ?: defaults.string(),
            nullType?.toScalaType()            ?: defaults.null(),
            bytesType?.toScalaType()           ?: defaults.bytes(),
            fixedType?.toScalaType()           ?: defaults.fixed(),
            recordType?.toScalaType()          ?: defaults.record(),
            enumType?.toScalaType()            ?: defaults.enum(),
            unionType?.toScalaType()           ?: defaults.union(),
            arrayType?.toScalaType()           ?: defaults.array(),
            mapType?.toScalaType()             ?: defaults.map(),
            protocolType?.toScalaType()        ?: defaults.protocol(),
            decimalType?.toScalaType()         ?: defaults.decimal(),
            dateType?.toScalaType()            ?: defaults.date(),
            timestampMillisType?.toScalaType() ?: defaults.timestampMillis(),
            uuidType?.toScalaType()            ?: defaults.uuid()
        )
    }
}
