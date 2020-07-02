package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaNumberType
import avrohugger.types.ScalaDouble$
import avrohugger.types.ScalaFloat$
import avrohugger.types.ScalaInt$
import avrohugger.types.ScalaLong$


interface NumberType {
    AvroScalaNumberType toScalaType()
}

enum NumberTypes implements NumberType {
    INT(ScalaInt$.MODULE$),
    LONG(ScalaLong$.MODULE$),
    FLOAT(ScalaFloat$.MODULE$),
    DOUBLE(ScalaDouble$.MODULE$)

    private AvroScalaNumberType scalaType

    NumberTypes(AvroScalaNumberType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaNumberType toScalaType() {
        return scalaType
    }
}