plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.serialization") version "2.2.10"

}

group = "com.seachange"
version = "1.0-SNAPSHOT"

val langChain4JVersion = "1.4.0"
val ktorClientVersion = "2.3.7"


repositories {
    mavenCentral()
}

dependencies {

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.13")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("ch.qos.logback:logback-classic:1.5.32")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.26.1")
    testImplementation("io.mockk:mockk:1.13.5")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}