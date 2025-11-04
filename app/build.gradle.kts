import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.google.services)
}

android {
    namespace = Config.Android.NAMESPACE
    compileSdk = Config.Android.COMPILE_SDK

    defaultConfig {
        applicationId = Config.Android.APPLICATION_ID
        minSdk = Config.Android.MIN_SDK
        targetSdk = Config.Android.TARGET_SDK
        versionCode = Config.Android.VERSION_CODE
        versionName = Config.Android.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        compose = true
        buildConfig = true
    }

    lint {
        lintConfig = File(rootDir, "lint.xml")
        checkDependencies = true
        ignoreTestSources = true
        warningsAsErrors = true
        checkGeneratedSources = false
    }
}

dependencies {
    implementation(projects.features.baseline)
    implementation(projects.features.navigation)
    implementation(projects.features.restapi)
    implementation(projects.features.scratch)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}