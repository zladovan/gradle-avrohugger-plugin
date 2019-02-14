# gradle-avrohugger-plugin

[Gradle](https://gradle.org) plugin for generating scala case classes from [Apache Avro](https://avro.apache.org/) schemas, datafiles and protocols. 
It is based on [avrohugger](https://github.com/julianpeeters/avrohugger) scala library.

## Usage

Minimally it is enough to add following to your `build.gradle` file:
                                    
```groovy
plugins {
    id 'com.zlad.gradle.avrohugger' version '0.1.3'
}
```

But usually `scala` plugin and `scala-library` dependency should be added to your `build.gradle` file 
to enable compilation of generated scala sources:

```groovy
plugins {
    id 'scala'
    id 'com.zlad.gradle.avrohugger' version '0.1.3'
}

repositories {
   mavenCentral()
}

dependencies {
    compile 'org.scala-lang:scala-library:2.12.8'
}
```

Then by default scala classes generation will be triggered during build before `compileScala` task. 
It will look into directory `src/main/avro` for all files ending with `.avsc`, `.avdl`, `.avro` or `.avpr`.
Classes will be generated under `${buildDir}/generated-src/avro` and they will be added to `main` scala source set.

> You can always call `avroScalaGenerate` task to perform classes generation only

## Configuration

You can change default configuration in `avrohugger` block like in following example:

```groovy
avrohugger {
    sourceDirectories = files('src-avro')
    destinationDirectory = file('src-scala')
    namespaceMapping = [ 'com.example' : 'com.zlad' ]
    typeMapping {
        protocolType = ScalaADT
    }
}
```

### Description of configuration values

| Name                       | Default                               | Description                              |
| -------------------------- | ------------------------------------- | ---------------------------------------- |
| sourceDirectories          | src/main/avro                         | Directories (can be multiple) used to look for `.avsc`, `.avdl`, `.avro` or `.avpr` files as an input for classes generation |
| destinationDirectory       | ${buildDir}/generated-src/avro        | Directory where classes will be generated |
| namespaceMapping           | empty map                             | Map where key is name of namespace and value is it's replacement which will be used as package for resulting classes |
| typeMapping                | default type mapping                  | Defines how avro types are represented as scala types |
  
### Type mapping values

| Name (avro type)           | Default (scala type)                 | PossibleValues                    |
| ---------------------------| -------------------------------------| ----------------------------------| 
| intType                    | ScalaInt                             | ScalaInt, ScalaLong, ScalaFloat, ScalaDouble |
| longType                   | ScalaLong                            | ScalaInt, ScalaLong, ScalaFloat, ScalaDouble |   
| floatType                  | ScalaFloat                           | ScalaInt, ScalaLong, ScalaFloat, ScalaDouble |   
| doubleType                 | ScalaDouble                          | ScalaInt, ScalaLong, ScalaFloat, ScalaDouble |   
| recordType                 | ScalaCaseClass                       | ScalaCaseClass, ScalaCaseClassWithSchema |
| enumType                   | ScalaEnumeration                     | ScalaEnumeration, JavaEnum, ScalaCaseObjectEnum, EnumAsScalaString |   
| unionType                  | OptionEitherShapelessCoproduct       | OptionEitherShapelessCoproduct, OptionalShapelessCoproduct, OptionShapelessCoproduct |   
| arrayType                  | ScalaSeq                             | ScalaSeq, ScalaArray, ScalaList, ScalaVector |   
| protocolType               | NoTypeGenerated                      | NoTypeGenerated, ScalaADT |

> See [avrohugger](https://github.com/julianpeeters/avrohugger) library for all details about types

### How to change default source set

If you do not want generated scala classes be a part of `main` source set you can change it as in following example to be a part of `test` source set:

```groovy
sourceSets {
    test {
        scala {
            srcDir avrohugger.destinationDirectory
        }
    }
}
```

## Scala 2.10 support

There is a restriction about maximum fields count for case classes in scala 2.10 and older.
If some of your records has more then 22 fields and scala 2.10 is used 
simple serializable class will be generated instead of case class.

>Plugin will get your scala version from `scala-library` version in compile dependencies. 
If it is not able to found the dependency it will act as the version is 2.11.  

## Build and Test

Plugin can be built and tested using:

    ./gradlew clean build

## Credits

Core generation logic is implemented by [Julian Peeters](https://github.com/julianpeeters) 
in scala library called [avrohugger](https://github.com/julianpeeters/avrohugger).
Plugin was heavily inspired by it's sbt version [sbt-avrohugger](https://github.com/julianpeeters/sbt-avrohugger). 
