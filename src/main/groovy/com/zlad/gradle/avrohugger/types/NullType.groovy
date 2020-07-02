package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaNullType
import avrohugger.types.ScalaNull$

interface NullType {
    AvroScalaNullType toScalaType()
}

enum NullTypes implements NullType {
    NULL(ScalaNull$.MODULE$)

    private AvroScalaNullType scalaType

    NullTypes(AvroScalaNullType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaNullType toScalaType() {
        return scalaType
    }
}


