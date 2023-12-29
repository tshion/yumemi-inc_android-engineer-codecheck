// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // Local variables
    apply(from = "variables.gradle")
}

plugins {
    // Android Gradle Plugin
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false

    // AndroidX Navigation
    id("androidx.navigation.safeargs") version "2.7.5" apply false

    // Firebase
    id("com.google.firebase.crashlytics") version "2.9.9" apply false

    // Google GMS Google Services Gradle Plugin
    id("com.google.gms.google-services") version "4.4.0" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.21" apply false

    // KSP
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
}