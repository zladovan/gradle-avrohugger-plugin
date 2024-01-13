package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaTimestampMillisType
import avrohugger.types.JavaSqlTimestamp$
import avrohugger.types.JavaTimeInstant$


interface TimestampMillisType {
    AvroScalaTimestampMillisType toScalaType()
}

enum TimestampMillisTypes implements TimestampMillisType {
    JAVA_TIME_INSTANT(JavaTimeInstant$.MODULE$),
    JAVA_SQL_INSTANT(JavaSqlTimestamp$.MODULE$)

    private AvroScalaTimestampMillisType scalaType

    TimestampMillisTypes(AvroScalaTimestampMillisType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaTimestampMillisType toScalaType() {
        return scalaType
    }
}
