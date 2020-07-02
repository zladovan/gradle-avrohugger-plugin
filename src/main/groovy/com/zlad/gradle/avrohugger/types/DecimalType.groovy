package com.zlad.gradle.avrohugger.types

import avrohugger.types.AvroScalaDecimalType
import avrohugger.types.ScalaBigDecimal
import avrohugger.types.ScalaBigDecimalWithPrecision
import scala.Enumeration
import scala.Option
import scala.math.BigDecimal

import java.math.RoundingMode


interface DecimalType {
    AvroScalaDecimalType toScalaType()
}

class ScalaBigDecimalType implements DecimalType {
    RoundingMode rounding

    @Override
    AvroScalaDecimalType toScalaType() {
        return new ScalaBigDecimal(RoundingModeMapping.toMaybeScalaType(rounding))
    }
}

class ScalaBigDecimalWithPrecisionType implements DecimalType {
    RoundingMode rounding

    @Override
    AvroScalaDecimalType toScalaType() {
        return new ScalaBigDecimalWithPrecision(RoundingModeMapping.toMaybeScalaType(rounding))
    }
}

class RoundingModeMapping {
    static Enumeration.Value toScalaType(RoundingMode javaType) {
        switch (javaType) {
            case RoundingMode.UP: return BigDecimal.RoundingMode$.MODULE$.UP()
            case RoundingMode.DOWN: return BigDecimal.RoundingMode$.MODULE$.DOWN()
            case RoundingMode.CEILING: return BigDecimal.RoundingMode$.MODULE$.CEILING()
            case RoundingMode.FLOOR: return BigDecimal.RoundingMode$.MODULE$.FLOOR()
            case RoundingMode.HALF_UP: return BigDecimal.RoundingMode$.MODULE$.HALF_UP()
            case RoundingMode.HALF_DOWN: return BigDecimal.RoundingMode$.MODULE$.HALF_DOWN()
            case RoundingMode.HALF_EVEN: return BigDecimal.RoundingMode$.MODULE$.HALF_EVEN()
            case RoundingMode.UNNECESSARY: return BigDecimal.RoundingMode$.MODULE$.UNNECESSARY()
        }
        throw new IllegalStateException("Unknown rounding mode: " + javaType)
    }

    static Option<Enumeration.Value> toMaybeScalaType(RoundingMode javaType) {
        return javaType == null ? Option.<Enumeration.Value>empty() : Option.apply(toScalaType(javaType))
    }
}