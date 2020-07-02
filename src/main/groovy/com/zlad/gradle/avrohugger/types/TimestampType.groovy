package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaTimestampMillisType
import avrohugger.types.JavaSqlTimestamp$
import avrohugger.types.JavaTimeInstant$


interface TimestampType {
    AvroScalaTimestampMillisType toScalaType()
}

enum TimestampTypes implements TimestampType {
    JAVA_TIME_INSTANT(JavaTimeInstant$.MODULE$),
    JAVA_SQL_INSTANT(JavaSqlTimestamp$.MODULE$)

    private AvroScalaTimestampMillisType scalaType

    TimestampTypes(AvroScalaTimestampMillisType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaTimestampMillisType toScalaType() {
        return scalaType
    }
}
