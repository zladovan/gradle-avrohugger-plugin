package com.zlad.gradle.avrohugger.types

import java.math.RoundingMode

/**
 * Constants for original avrohugger scala type objects to provide nice names in DSL.
 */
interface CustomTypesValues {

    // numbers
    NumberType ScalaInt = NumberTypes.INT
    NumberType ScalaLong = NumberTypes.LONG
    NumberType ScalaFloat = NumberTypes.FLOAT
    NumberType ScalaDouble = NumberTypes.DOUBLE

    // boolean
    BoolType ScalaBoolean = BoolTypes.BOOL

    // string
    StringType ScalaString = StringTypes.STRING

    // null
    NullType ScalaNull = NullTypes.NULL

    // bytes
    BytesType ScalaByteArray = BytesTypes.BYTE_ARRAY

    // fixed
    FixedType ScalaCaseClassWrapper = FixedTypes.CASE_CLASS_WRAPPER
    FixedType ScalaCaseClassWrapperWithSchema = FixedTypes.CASE_CLASS_WRAPPER_WITH_SCHEMA

    // record
    RecordType ScalaCaseClass = RecordTypes.CASE_CLASS
    RecordType ScalaCaseClassWithSchema = RecordTypes.CASE_CLASS_WITH_SCHEMA

    // enum
    EnumType ScalaEnumeration = EnumTypes.ENUMERATION
    EnumType JavaEnum = EnumTypes.JAVA_ENUM
    EnumType ScalaCaseObjectEnum = EnumTypes.CASE_OBJECT_ENUM
    EnumType EnumAsScalaString = EnumTypes.ENUM_AS_STRING

    // union
    UnionType OptionalShapelessCoproduct = UnionTypes.OPTIONAL_SHAPELESS_COPRODUCT
    UnionType OptionShapelessCoproduct = UnionTypes.OPTION_SHAPELESS_COPRODUCT
    UnionType OptionEitherShapelessCoproduct = UnionTypes.OPTION_EITHER_SHAPELESS_COPRODUCT

    // arrray
    ArrayType ScalaArray = ArrayTypes.ARRAY
    ArrayType ScalaList = ArrayTypes.LIST
    ArrayType ScalaSeq = ArrayTypes.SEQ
    ArrayType ScalaVector = ArrayTypes.VECTOR

    // map
    MapType ScalaMap = MapTypes.MAP

    // protocol
    ProtocolType ScalaADT = ProtocolTypes.ADT
    ProtocolType NoTypeGenerated = ProtocolTypes.NO_TYPE_GENERATED

    // decimal
    DecimalType ScalaBigDecimal =  new ScalaBigDecimalType()
    DecimalType ScalaBigDecimal_UP = new ScalaBigDecimalType(rounding: RoundingMode.UP)
    DecimalType ScalaBigDecimal_DOWN = new ScalaBigDecimalType(rounding: RoundingMode.DOWN)
    DecimalType ScalaBigDecimal_CEILING = new ScalaBigDecimalType(rounding: RoundingMode.CEILING)
    DecimalType ScalaBigDecimal_FLOOR = new ScalaBigDecimalType(rounding: RoundingMode.FLOOR)
    DecimalType ScalaBigDecimal_HALF_UP = new ScalaBigDecimalType(rounding: RoundingMode.HALF_UP)
    DecimalType ScalaBigDecimal_HALF_DOWN = new ScalaBigDecimalType(rounding: RoundingMode.HALF_DOWN)
    DecimalType ScalaBigDecimal_HALF_EVEN = new ScalaBigDecimalType(rounding: RoundingMode.HALF_EVEN)
    DecimalType ScalaBigDecimal_UNNECESSARY = new ScalaBigDecimalType(rounding: RoundingMode.UNNECESSARY)
    DecimalType ScalaBigDecimalWithPrecision = new ScalaBigDecimalWithPrecisionType()
    DecimalType ScalaBigDecimalWithPrecision_UP = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.UP)
    DecimalType ScalaBigDecimalWithPrecision_DOWN = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.DOWN)
    DecimalType ScalaBigDecimalWithPrecision_CEILING = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.CEILING)
    DecimalType ScalaBigDecimalWithPrecision_FLOOR = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.FLOOR)
    DecimalType ScalaBigDecimalWithPrecision_HALF_UP = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.HALF_UP)
    DecimalType ScalaBigDecimalWithPrecision_HALF_DOWN = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.HALF_DOWN)
    DecimalType ScalaBigDecimalWithPrecision_HALF_EVEN = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.HALF_EVEN)
    DecimalType ScalaBigDecimalWithPrecision_UNNECESSARY = new ScalaBigDecimalWithPrecisionType(rounding: RoundingMode.UNNECESSARY)

    // date
    DateType JavaTimeLocalDate = DateTypes.JAVA_TIME_LOCAL_DATE
    DateType JavaSqlDate = DateTypes.JAVA_SQL_DATE

    // timestamp millis
    TimestampType JavaTimeInstant = TimestampTypes.JAVA_TIME_INSTANT
    TimestampType JavaSqlTimestamp = TimestampTypes.JAVA_SQL_INSTANT

    // time millis
    TimeType JavaTimeLocalTime = TimeTypes.JAVA_TIME_LOCAL_TIME
    TimeType JavaSqlTime = TimeTypes.JAVA_SQL_TIME

    // uuid
    UuidType JavaUuid = UuidTypes.JAVA_UUID
}












