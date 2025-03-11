package com.zlad.gradle.avrohugger

import scala.collection.JavaConverters$


class ScalaConversions {

    static <K, V> scala.collection.immutable.Map<K, V> convert(Map<K, V> map) {
        return scala.collection.immutable.Map$.MODULE$.from(
                JavaConverters$.MODULE$.mapAsScalaMapConverter(map).asScala()
        )
    }
}
