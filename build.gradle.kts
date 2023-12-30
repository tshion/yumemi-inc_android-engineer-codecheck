// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // Local variables
    apply(from = "variables.gradle")
}

plugins {
    // Android Gradle Plugin
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Android Jetpack
    alias(libs.plugins.jetpack.navigation) apply false

    // Firebase
    alias(libs.plugins.firebase.crashlytics) apply false

    // Google GMS Google Services Gradle Plugin
    alias(libs.plugins.gms.googleServices) apply false

    // Kotlin
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false

    // KSP
    alias(libs.plugins.ksp) apply false
}
true // Needed to make the Suppress annotation work for the plugins block