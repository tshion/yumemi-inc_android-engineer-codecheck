import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}

dependencies {

    // AndroidX
    implementation(libs.androidx.annotation)

    // JUnit
    testImplementation(libs.junit)

    // Kotlin Coroutines
    testImplementation(platform(libs.kotlinx.coroutines.bom))
    testImplementation(libs.kotlinx.coroutines.test)

    // MockK
    testImplementation(libs.mockK)
}