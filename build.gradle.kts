// Top-level build file where you can add configuration options common to all sub-projects/modules.
ext {
    // アプリ構成
    extra["androidApiMin"] = 23
    extra["androidApiTarget"] = 34

    // アプリバージョン
    val appVersionMajor = 1
    val appVersionMinor = 2
    val appVersionPatch = 2
    extra["appVersionCode"] = 10000 * appVersionMajor + 100 * appVersionMinor + appVersionPatch
    extra["appVersionName"] = "${appVersionMajor}.${appVersionMinor}.${appVersionPatch}"
}

plugins {
    // Android Gradle Plugin
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Android Jetpack
    alias(libs.plugins.androidx.navigation) apply false

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