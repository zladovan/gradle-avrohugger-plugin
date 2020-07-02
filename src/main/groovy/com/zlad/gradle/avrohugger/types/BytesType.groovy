package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaBytesType
import avrohugger.types.ScalaByteArray$


interface BytesType {
    AvroScalaBytesType toScalaType()
}

enum BytesTypes implements BytesType {
    BYTE_ARRAY(ScalaByteArray$.MODULE$)

    private AvroScalaBytesType scalaType

    BytesTypes(AvroScalaBytesType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaBytesType toScalaType() {
        return scalaType
    }
}
