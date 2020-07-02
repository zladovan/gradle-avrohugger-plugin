package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaRecordType
import avrohugger.types.ScalaCaseClass$
import avrohugger.types.ScalaCaseClassWithSchema$

interface RecordType {
    AvroScalaRecordType toScalaType()
}

enum RecordTypes implements RecordType {
    CASE_CLASS(ScalaCaseClass$.MODULE$),
    CASE_CLASS_WITH_SCHEMA(ScalaCaseClassWithSchema$.MODULE$)

    private AvroScalaRecordType scalaType

    RecordTypes(AvroScalaRecordType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaRecordType toScalaType() {
        return scalaType
    }
}