// Top-level build file where you can add configuration options common to all sub-projects/modules.
ext {
    // アプリ構成
    extra["androidApiMin"] = 23
    extra["androidApiTarget"] = 34

    // アプリバージョン
    extra["appVersionCode"] = 10202
    extra["appVersionName"] = "1.2.2"
}

/**
 * アプリバージョンの設定
 */
tasks.register("setVersion") {
    doLast {
        val args = "${project.properties["args"]}".split(" ")
        if (args.count() != 3) {
            throw IllegalArgumentException("引数を３つ指定してください")
        }

        val major = args[0].toIntOrNull() ?: -1
        if (major < 0) {
            throw IllegalArgumentException("major には正整数を指定してください")
        }

        val minor = args[1].toIntOrNull() ?: -1
        if (minor !in 0..99) {
            throw IllegalArgumentException("minor には1 ~ 2桁の正整数を指定してください")
        }

        val patch = args[2].toIntOrNull() ?: -1
        if (minor !in 0..99) {
            throw IllegalArgumentException("patch には1 ~ 2桁の正整数を指定してください")
        }


        // バージョン情報の算出
        val versionCode = major * 10000 + minor * 100 + patch
        val versionName = "${major}.${minor}.${patch}"


        // ファイル出力
        val file = project.rootProject.file("build.gradle.kts")
        file.readText()
            .replace(
                Regex("""(extra\["appVersionCode"\] = )\d+"""),
                "$1$versionCode",
            )
            .replace(
                Regex("""(extra\["appVersionName"\] = ")\d[\d\.]{0,}\d(")"""),
                "$1$versionName$2",
            )
            .also { file.writeText(it) }


        // 終了表示
        println("Set code: $versionCode, name: $versionName")
    }
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