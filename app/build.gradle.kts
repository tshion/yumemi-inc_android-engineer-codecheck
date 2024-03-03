import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")

    // Firebase Crashlytics Gradle
    alias(libs.plugins.firebase.crashlytics)

    // Google GMS
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.gms.ossLicenses)
}


// アプリビルド情報の読み取り
val buildProperties = Properties()
buildProperties.load(FileInputStream(rootProject.file("build.properties")))

// keystore.properties の読み取り
val keystoreProperties = Properties()
try {
    keystoreProperties.load(FileInputStream(rootProject.file("keystore.properties")))
} catch (_: Exception) {
}


android {
    namespace = "jp.co.yumemi.android.code_check"
    compileSdk = rootProject.extra["androidApiTarget"] as Int

    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "jp.co.yumemi.android.codecheck"
        minSdk = rootProject.extra["androidApiMin"] as Int
        targetSdk = rootProject.extra["androidApiTarget"] as Int
        versionCode = "${buildProperties["version_code"]}".toIntOrNull()
        versionName = "${buildProperties["version_name"]}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        val file = rootProject.file("release.jks")
        if (file.exists()) {
            create("release") {
                storeFile = file
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                    ?: keystoreProperties.getProperty("KEYSTORE_PASSWORD", "")
                keyAlias = System.getenv("KEY_ALIAS")
                    ?: keystoreProperties.getProperty("KEY_ALIAS", "")
                keyPassword = System.getenv("KEY_PASSWORD")
                    ?: keystoreProperties.getProperty("KEY_PASSWORD", "")
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs["release"]
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Local Modules
    implementation(project(":app_core"))
    implementation(project(":github_web_api"))

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.recyclerview)
    androidTestImplementation(libs.androidx.uiautomator)

    // Auto Service
    kaptDebug(libs.autoservice)

    // Coil
    implementation(libs.coil)

    // Desugar
    coreLibraryDesugaring(libs.desugar)

    // Firebase
    releaseImplementation(platform(libs.firebase.bom))
    releaseImplementation(libs.firebase.crashlytics)

    // GMS
    implementation(libs.gms.ossLicenses)

    // Hyperion
    debugImplementation(libs.hyperion.attr)
    debugImplementation(libs.hyperion.core)
    debugImplementation(libs.hyperion.measurement)
    debugImplementation(libs.hyperion.plugin)
    debugImplementation(libs.hyperion.timber)

    // Kotlin Coroutines
    implementation(platform(libs.kotlinx.coroutines.bom))
    implementation(libs.kotlinx.coroutines.android)

    // LeakCanary
    debugImplementation(libs.leakCanary)

    // Material Design
    implementation(libs.material)

    // OkHttp
    implementation(platform(libs.okhttp.bom))
    debugImplementation(libs.okhttp.logging)
    implementation(libs.okhttp.okhttp)

    // Timber
    implementation(libs.timber)
}


// アプリバージョン名の表示
tasks.register("showVersionName") {
    val file = rootProject.file("./app/build/outputs/apk/release/output-metadata.json")
    var versionName = "unknown"
    if (file.exists()) {
        val text = file.readText()
        val matches = Regex(""""versionName": "(.+)",""").findAll(text)
        versionName = matches.first().groupValues[1]
    }
    println(versionName)
}


// app/google-services.json がある場合のみセットアップ
if (rootProject.file("app/google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
}
