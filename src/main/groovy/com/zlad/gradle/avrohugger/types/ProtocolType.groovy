package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaProtocolType
import avrohugger.types.NoTypeGenerated$
import avrohugger.types.ScalaADT$


interface ProtocolType {
    AvroScalaProtocolType toScalaType()
}

enum ProtocolTypes implements ProtocolType {
    ADT(ScalaADT$.MODULE$),
    NO_TYPE_GENERATED(NoTypeGenerated$.MODULE$)

    private AvroScalaProtocolType scalaType

    ProtocolTypes(AvroScalaProtocolType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaProtocolType toScalaType() {
        return scalaType
    }
}
