package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroUuidType
import avrohugger.types.JavaUuid$


interface UuidType {
    AvroUuidType toScalaType()
}

enum UuidTypes implements UuidType {
    JAVA_UUID(JavaUuid$.MODULE$)

    private AvroUuidType scalaType

    UuidTypes(AvroUuidType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroUuidType toScalaType() {
        return scalaType
    }
}