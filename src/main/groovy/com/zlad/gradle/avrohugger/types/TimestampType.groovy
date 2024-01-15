package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaTimestampType
import avrohugger.types.JavaTimeZonedDateTime$

interface TimestampType {
  AvroScalaTimestampType toScalaType()
}

enum TimestampTypes implements TimestampType {
  JAVA_TIME_ZONED_DATE_TIME(JavaTimeZonedDateTime$.MODULE$)

  private AvroScalaTimestampType scalaType

  TimestampTypes(AvroScalaTimestampType scalaType) {
    this.scalaType = scalaType
  }

  @Override
  AvroScalaTimestampType toScalaType() {
    return scalaType
  }
}
