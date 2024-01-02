import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}

dependencies {

    // JUnit
    testImplementation(libs.junit)

    // Kotlin Coroutines
    testImplementation(platform(libs.kotlinx.coroutines.bom))
    testImplementation(libs.kotlinx.coroutines.test)

    // Moshi
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.codegen)

    // OkHttp
    implementation(platform(libs.okhttp.bom))
    testImplementation(libs.okhttp.mock)
    implementation(libs.okhttp.okhttp)

    // Retrofit
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.retrofit)
}