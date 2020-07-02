package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaFixedType
import avrohugger.types.ScalaBinary$

interface FixedType {
    AvroScalaFixedType toScalaType()
}

enum FixedTypes implements FixedType {
    BINARY(ScalaBinary$.MODULE$)

    private AvroScalaFixedType scalaType

    FixedTypes(AvroScalaFixedType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaFixedType toScalaType() {
        return scalaType
    }
}