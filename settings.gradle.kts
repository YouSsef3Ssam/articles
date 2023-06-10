@file:Suppress("UnstableApiUsage")

rootProject.name = "Articles"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        jcenter()
    }
}

include(
    ":app",

    //core modules
    ":core:network",

    //common modules
    ":common:ui",
    ":common:utils",

    //features modules
    ":feature:articles"
)