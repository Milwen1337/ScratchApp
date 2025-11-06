import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "${Config.Android.NAMESPACE}.usecase"
    compileSdk = Config.Android.COMPILE_SDK

    defaultConfig {
        minSdk = Config.Android.MIN_SDK
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21

        }
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    implementation(projects.features.database)
    implementation(projects.features.scratch)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.moshi.moshi)
    ksp(libs.moshi.kotlin.codegen)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

}