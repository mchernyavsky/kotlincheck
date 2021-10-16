plugins {
    kotlin("jvm") version "1.5.31"
}

group = "io.kotlincheck"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    implementation("io.kotlintest:kotlintest:2.0.7")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    implementation("com.h2database:h2:1.4.200")
    implementation("org.jetbrains.exposed:exposed:0.17.14")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}
