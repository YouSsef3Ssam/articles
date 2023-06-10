@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = NamceSpace.applicationId
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ReleaseVersion.versionCode
        versionName = ReleaseVersion.versionName

        testInstrumentationRunner = ConfigData.testRunner
    }

    signingConfigs {
        create(BuildTypeRelease.name) {
            storeFile = file(KeyHelper.getValue(KeyHelper.KEY_STORE_FILE))
            storePassword = KeyHelper.getValue(KeyHelper.KEY_STORE_PASS)
            keyAlias = KeyHelper.getValue(KeyHelper.KEY_ALIAS)
            keyPassword = KeyHelper.getValue(KeyHelper.KEY_PASS)
        }
    }

    buildTypes {
        getByName(BuildTypeDebug.name) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isShrinkResources = BuildTypeDebug.isShrinkResources
            isDebuggable = BuildTypeDebug.isDebuggable
        }

        getByName(BuildTypeRelease.name) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResources
            isDebuggable = BuildTypeRelease.isDebuggable

            signingConfig = signingConfigs.getByName(BuildTypeRelease.name)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        applicationVariants.all {
            val variant = this
            variant.outputs
                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    output.outputFileName = createApkName(variant.baseName, variant.versionName)
                }
        }
    }

    flavorDimensions.add(BuildVariant.enviromentDimension)
    productFlavors {
        create(BuildVariant.Flavor.Enviroment.staging) {
            dimension = BuildVariant.enviromentDimension
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            manifestPlaceholders["appIcon"] = "@drawable/ic_debug_icon"
            manifestPlaceholders["appIconRound"] = "@drawable/ic_debug_icon"
        }

        create(BuildVariant.Flavor.Enviroment.production) {
            dimension = BuildVariant.enviromentDimension
            manifestPlaceholders["appIcon"] = "@drawable/logo"
            manifestPlaceholders["appIconRound"] = "@drawable/logo"
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.ui)
    implementation(libs.splash)
    implementation(libs.timber)

    implementation(libs.hilt)
    kapt(libs.hiltDaggerCompiler)

    debugImplementation(libs.plutoDebug)
    debugImplementation(libs.plutoNetworkDebug)
    releaseImplementation(libs.plutoRelease)
    releaseImplementation(libs.plutoNetworkRelease)

    implementation(project(Modules.Features.articles))
    implementation(project(Modules.Commons.ui))
}

// Run ktlintCheck, ktlintFormat and detekt tasks before build the application
tasks.applyLinitingtasks()