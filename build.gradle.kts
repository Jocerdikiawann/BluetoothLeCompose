buildscript{
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.android.application") version "7.2.0-beta01" apply false
    id("com.android.library") version "7.2.0-beta01" apply false
    kotlin("android") version Version.KotlinAndroidVersion /*"1.5.31"*/ apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
}

tasks.create<Delete>("cleanRp") {
    delete(
        rootProject.buildDir
    )
}