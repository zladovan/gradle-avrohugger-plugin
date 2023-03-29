package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaFixedType
import avrohugger.types.ScalaCaseClassWrapper$
import avrohugger.types.ScalaCaseClassWrapperWithSchema$

interface FixedType {
    AvroScalaFixedType toScalaType()
}

enum FixedTypes implements FixedType {
    CASE_CLASS_WRAPPER(ScalaCaseClassWrapper$.MODULE$),
    CASE_CLASS_WRAPPER_WITH_SCHEMA(ScalaCaseClassWrapperWithSchema$.MODULE$)

    private AvroScalaFixedType scalaType

    FixedTypes(AvroScalaFixedType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaFixedType toScalaType() {
        return scalaType
    }
}
