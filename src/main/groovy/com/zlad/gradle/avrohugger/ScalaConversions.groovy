package com.zlad.gradle.avrohugger

import scala.collection.JavaConverters$


class ScalaConversions {

    static <K, V> scala.collection.immutable.Map<K, V> convert(Map<K, V> map) {
        return JavaConverters$.MODULE$.mapAsScalaMapConverter(map).asScala().toMap(
                scala.Predef$.MODULE$.<scala.Tuple2<K, V>>conforms()
        )
    }
}
