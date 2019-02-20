package com.zlad.gradle.avrohugger

/**
 * Constants providing nice names of source formats in DSL without need to do import.
 */
interface SourceFormatValues {

    ScalaSourceFormat Standard = ScalaSourceFormat.Standard

    ScalaSourceFormat SpecificRecord = ScalaSourceFormat.SpecificRecord

    ScalaSourceFormat Scavro = ScalaSourceFormat.Scavro
}
