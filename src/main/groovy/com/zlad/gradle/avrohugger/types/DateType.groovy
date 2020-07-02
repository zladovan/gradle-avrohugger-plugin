package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaDateType
import avrohugger.types.JavaSqlDate$
import avrohugger.types.JavaTimeLocalDate$


interface DateType {
    AvroScalaDateType toScalaType()
}

enum DateTypes implements DateType {
    JAVA_TIME_LOCAL_DATE(JavaTimeLocalDate$.MODULE$),
    JAVA_SQL_DATE(JavaSqlDate$.MODULE$)

    private AvroScalaDateType scalaType

    DateTypes(AvroScalaDateType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaDateType toScalaType() {
        return scalaType
    }
}