plugins {
    kotlin("jvm")
}

group = "dev.thelumiereguy"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(fileTree("src/main/libs") { include("*.jar") })
}