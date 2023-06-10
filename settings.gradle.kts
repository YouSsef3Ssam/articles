@file:Suppress("UnstableApiUsage")

rootProject.name = "Articles"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

include(
    ":app",

    //core modules
    ":core:network",

    //common modules
    ":common:ui",
    ":common:utils",
    ":common:uiTest",

    //features modules
    ":feature:articles"
)