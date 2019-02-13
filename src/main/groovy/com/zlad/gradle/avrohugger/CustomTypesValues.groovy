package com.zlad.gradle.avrohugger

import avrohugger.types.EnumAsScalaString$
import avrohugger.types.JavaEnum$
import avrohugger.types.NoTypeGenerated$
import avrohugger.types.OptionEitherShapelessCoproduct$
import avrohugger.types.OptionShapelessCoproduct$
import avrohugger.types.OptionalShapelessCoproduct$
import avrohugger.types.ScalaADT$
import avrohugger.types.ScalaArray$
import avrohugger.types.ScalaBinary$
import avrohugger.types.ScalaBoolean$
import avrohugger.types.ScalaByteArray$
import avrohugger.types.ScalaCaseClass$
import avrohugger.types.ScalaCaseClassWithSchema$
import avrohugger.types.ScalaCaseObjectEnum$
import avrohugger.types.ScalaDouble$
import avrohugger.types.ScalaEnumeration$
import avrohugger.types.ScalaFloat$
import avrohugger.types.ScalaInt$
import avrohugger.types.ScalaList$
import avrohugger.types.ScalaLong$
import avrohugger.types.ScalaMap$
import avrohugger.types.ScalaNull$
import avrohugger.types.ScalaSeq$
import avrohugger.types.ScalaString$
import avrohugger.types.ScalaVector$

/**
 * Constants for original avrohugger scala type objects to provide nice names in DSL.
 */
interface CustomTypesValues {

    // numbers
    ScalaInt$ ScalaInt = ScalaInt$.MODULE$
    ScalaLong$ ScalaLong = ScalaLong$.MODULE$
    ScalaFloat$ ScalaFloat = ScalaFloat$.MODULE$
    ScalaDouble$ ScalaDouble = ScalaDouble$.MODULE$

    // boolean
    ScalaBoolean$ ScalaBoolean = ScalaBoolean$.MODULE$

    // string
    ScalaString$ ScalaString = ScalaString$.MODULE$

    // null
    ScalaNull$ ScalaNull = ScalaNull$.MODULE$

    // bytes
    ScalaByteArray$ ScalaByteArray = ScalaByteArray$.MODULE$

    // fixed
    ScalaBinary$ ScalaBinary = ScalaBinary$.MODULE$

    // record
    ScalaCaseClass$ ScalaCaseClass = ScalaCaseClass$.MODULE$
    ScalaCaseClassWithSchema$ ScalaCaseClassWithSchema = ScalaCaseClassWithSchema$.MODULE$

    // enum
    ScalaEnumeration$ ScalaEnumeration = ScalaEnumeration$.MODULE$
    JavaEnum$ JavaEnum = JavaEnum$.MODULE$
    ScalaCaseObjectEnum$ ScalaCaseObjectEnum = ScalaCaseObjectEnum$.MODULE$
    EnumAsScalaString$ EnumAsScalaString = EnumAsScalaString$.MODULE$

    // union
    OptionalShapelessCoproduct$ OptionalShapelessCoproduct = OptionalShapelessCoproduct$.MODULE$
    OptionShapelessCoproduct$ OptionShapelessCoproduct = OptionShapelessCoproduct$.MODULE$
    OptionEitherShapelessCoproduct$ OptionEitherShapelessCoproduct = OptionEitherShapelessCoproduct$.MODULE$

    // arrray
    ScalaArray$ ScalaArray = ScalaArray$.MODULE$
    ScalaList$ ScalaList = ScalaList$.MODULE$
    ScalaSeq$ ScalaSeq = ScalaSeq$.MODULE$
    ScalaVector$ ScalaVector = ScalaVector$.MODULE$

    // map
    ScalaMap$ ScalaMap = ScalaMap$.MODULE$

    // protocol
    ScalaADT$ ScalaADT = ScalaADT$.MODULE$
    NoTypeGenerated$ NoTypeGenerated = NoTypeGenerated$.MODULE$
}
