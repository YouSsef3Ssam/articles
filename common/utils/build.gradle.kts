@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.secrets)
}

secrets {
    defaultPropertiesFileName = ConfigData.secretsFileName
}

android {
    compileSdk = ConfigData.compileSdk
    namespace = NamceSpace.Common.utils

    defaultConfig {
        minSdk = ConfigData.minSdk
    }

    buildTypes {
        getByName(BuildTypeDebug.name) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        getByName(BuildTypeRelease.name) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            consumerProguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.kotlinJvmTarget.get()
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions.add(BuildVariant.enviromentDimension)
    productFlavors {
        create(BuildVariant.Flavor.Enviroment.staging) {
            dimension = BuildVariant.enviromentDimension
        }

        create(BuildVariant.Flavor.Enviroment.production) {
            dimension = BuildVariant.enviromentDimension
        }
    }
}

dependencies {
    implementation(libs.navigationFragment)
    implementation(libs.timber)

    implementation(libs.hilt)
    kapt(libs.hiltDaggerCompiler)
}

// Run ktlintCheck, ktlintFormat and detekt tasks before build the application
tasks.applyLinitingtasks()