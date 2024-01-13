package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaTimeType
import avrohugger.types.JavaTimeLocalTime$

interface TimeType {
  AvroScalaTimeType toScalaType()
}

enum TimeTypes implements TimeType {
  JAVA_TIME_LOCAL_TIME(JavaTimeLocalTime$.MODULE$)

  private AvroScalaTimeType scalaType

  TimeTypes(AvroScalaTimeType scalaType) {
    this.scalaType = scalaType
  }

  @Override
  AvroScalaTimeType toScalaType() {
    return scalaType
  }
}
