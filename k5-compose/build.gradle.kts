plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.1.1"
}

group = "com.github.CreativeCodingExamples"
version = "1.0.0"

dependencies {
    implementation(compose.desktop.macos_arm64)
    implementation(kotlin("stdlib"))
    implementation("me.nikhilchaudhari:k5-compose:1.0.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "K5ComposeMainKt"
        nativeDistributions{
            packageName = "k5-compose"
        }
    }
}