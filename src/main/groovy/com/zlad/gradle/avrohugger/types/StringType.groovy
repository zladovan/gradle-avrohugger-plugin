package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaStringType
import avrohugger.types.ScalaString$

interface StringType {
    AvroScalaStringType toScalaType()
}

enum StringTypes implements StringType {
    STRING(ScalaString$.MODULE$)

    private AvroScalaStringType scalaType

    StringTypes(AvroScalaStringType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaStringType toScalaType() {
        return scalaType
    }
}