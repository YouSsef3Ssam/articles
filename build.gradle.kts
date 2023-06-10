// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jlleitschuh.gradle.ktlint.KtlintExtension

val ktlintPlugin by extra { libs.plugins.ktlint.get().pluginId }
val detektPlugin by extra { libs.plugins.detekt.get().pluginId }

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    val ktlintPlugin: String by rootProject.extra
    val detektPlugin: String by rootProject.extra
    apply(plugin = ktlintPlugin) // To apply ktLint to all included modules
    apply(plugin = detektPlugin) // To apply Detekt to all included modules

    configure<KtlintExtension> {
        debug.set(true)
        android.set(true)
        ignoreFailures.set(false)
        disabledRules.set(setOf("final-newline"))
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}