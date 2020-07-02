package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaArrayType
import avrohugger.types.ScalaArray$
import avrohugger.types.ScalaList$
import avrohugger.types.ScalaSeq$
import avrohugger.types.ScalaVector$

interface ArrayType {
    AvroScalaArrayType toScalaType()
}

enum ArrayTypes implements ArrayType {
    ARRAY(ScalaArray$.MODULE$),
    LIST(ScalaList$.MODULE$),
    SEQ(ScalaSeq$.MODULE$),
    VECTOR(ScalaVector$.MODULE$)

    private AvroScalaArrayType scalaType

    ArrayTypes(AvroScalaArrayType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaArrayType toScalaType() {
        return scalaType
    }
}