@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.parcelize)
}

android {
    compileSdk = ConfigData.compileSdk
    namespace = NamceSpace.Feature.articles

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

    hilt {
        enableAggregatingTask = true
    }

    buildFeatures {
        viewBinding = true
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
    implementation(project(Modules.Core.network))
    implementation(project(Modules.Commons.utils))
    implementation(project(Modules.Commons.ui))

    implementation(libs.bundles.ui)
    implementation(libs.bundles.navigationComponent)

    implementation(libs.hilt)
    kapt(libs.hiltDaggerCompiler)

    testImplementation(libs.bundles.unitTest)
}

// Run ktlintCheck, ktlintFormat and detekt tasks before build the application
tasks.applyLinitingtasks()