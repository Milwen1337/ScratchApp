plugins {
    `kotlin-dsl`
}
kotlin {
    jvmToolchain(JavaVersion.VERSION_21.majorVersion.toInt())
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {


}
