// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // Local variables
    apply(from = "variables.gradle")
}

plugins {
    // Android Gradle Plugin
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false

    // AndroidX Navigation
    alias(libs.plugins.jetpackNavigationSafeargs) apply false

    // Firebase
    alias(libs.plugins.firebaseCrashlytics) apply false

    // Google GMS Google Services Gradle Plugin
    alias(libs.plugins.gmsGoogleServices) apply false

    // Kotlin
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinJvm) apply false

    // KSP
    alias(libs.plugins.ksp) apply false
}
true // Needed to make the Suppress annotation work for the plugins block