package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaEnumType
import avrohugger.types.EnumAsScalaString$
import avrohugger.types.JavaEnum$
import avrohugger.types.ScalaCaseObjectEnum$
import avrohugger.types.ScalaEnumeration$


interface EnumType {
    AvroScalaEnumType toScalaType()
}

enum EnumTypes implements EnumType {
    ENUMERATION(ScalaEnumeration$.MODULE$),
    JAVA_ENUM(JavaEnum$.MODULE$),
    CASE_OBJECT_ENUM(ScalaCaseObjectEnum$.MODULE$),
    ENUM_AS_STRING(EnumAsScalaString$.MODULE$)

    private AvroScalaEnumType scalaType

    EnumTypes(AvroScalaEnumType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaEnumType toScalaType() {
        return scalaType
    }
}