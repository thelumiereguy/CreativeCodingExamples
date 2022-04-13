plugins {
    kotlin("jvm") version "1.6.10"
    id("com.android.application") version "7.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

group = "dev.thelumiereguy"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
