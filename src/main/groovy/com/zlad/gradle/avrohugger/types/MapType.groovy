package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaMapType
import avrohugger.types.ScalaMap$

interface MapType {
    AvroScalaMapType toScalaType()
}

enum MapTypes implements MapType {
    MAP(ScalaMap$.MODULE$)

    private AvroScalaMapType scalaType

    MapTypes(AvroScalaMapType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaMapType toScalaType() {
        return scalaType
    }
}
