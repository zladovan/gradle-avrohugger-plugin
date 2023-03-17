package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaTimeMillisType
import avrohugger.types.JavaSqlTime$
import avrohugger.types.JavaTimeLocalTime$

interface TimeType {
  AvroScalaTimeMillisType toScalaType()
}

enum TimeTypes implements TimeType {
  JAVA_TIME_LOCAL_TIME(JavaTimeLocalTime$.MODULE$),
  JAVA_SQL_TIME(JavaSqlTime$.MODULE$)

  private AvroScalaTimeMillisType scalaType

  TimeTypes(AvroScalaTimeMillisType scalaType) {
    this.scalaType = scalaType
  }

  @Override
  AvroScalaTimeMillisType toScalaType() {
    return scalaType
  }
}
