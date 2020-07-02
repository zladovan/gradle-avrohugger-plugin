package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaUnionType
import avrohugger.types.OptionEitherShapelessCoproduct$
import avrohugger.types.OptionShapelessCoproduct$
import avrohugger.types.OptionalShapelessCoproduct$


interface UnionType {
    AvroScalaUnionType toScalaType()
}

enum UnionTypes implements UnionType {
    OPTIONAL_SHAPELESS_COPRODUCT(OptionalShapelessCoproduct$.MODULE$),
    OPTION_SHAPELESS_COPRODUCT(OptionShapelessCoproduct$.MODULE$),
    OPTION_EITHER_SHAPELESS_COPRODUCT(OptionEitherShapelessCoproduct$.MODULE$)

    private AvroScalaUnionType scalaType

    UnionTypes(AvroScalaUnionType scalaType) {
        this.scalaType = scalaType
    }

    @Override
    AvroScalaUnionType toScalaType() {
        return scalaType
    }
}