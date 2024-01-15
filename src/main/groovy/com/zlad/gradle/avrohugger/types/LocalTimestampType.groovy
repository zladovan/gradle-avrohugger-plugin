package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaLocalTimestampType
import avrohugger.types.JavaTimeLocalDateTime$

interface LocalTimestampType {
  AvroScalaLocalTimestampType toScalaType()
}

enum LocalTimestampTypes implements LocalTimestampType {
  JAVA_LOCAL_DATE_TIME(JavaTimeLocalDateTime$.MODULE$)

  private AvroScalaLocalTimestampType scalaType

  LocalTimestampTypes(AvroScalaLocalTimestampType scalaType) {
    this.scalaType = scalaType
  }

  @Override
  AvroScalaLocalTimestampType toScalaType() {
    return scalaType
  }
}
