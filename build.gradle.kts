import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var kotlin_version:String by extra

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.1.4-3"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}
plugins {
    application
}
apply {
    plugin("kotlin")
}

application {
    mainClassName = "manager.AppKt"
}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlin_version")
    compile("com.github.shaunxiao:kotlinGameEngine:v0.0.2")
}

//
//compileKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}
//compileTestKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}