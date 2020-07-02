package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaBooleanType
import avrohugger.types.ScalaBoolean$


interface BoolType {
    AvroScalaBooleanType toScalaType()
}

enum BoolTypes implements BoolType {
    BOOL(ScalaBoolean$.MODULE$)

    private AvroScalaBooleanType scalaType

    BoolTypes(AvroScalaBooleanType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaBooleanType toScalaType() {
        return scalaType
    }
}
