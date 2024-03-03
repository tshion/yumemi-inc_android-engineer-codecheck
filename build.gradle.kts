// Top-level build file where you can add configuration options common to all sub-projects/modules.
ext {
    // アプリ構成
    extra["androidApiMin"] = 23
    extra["androidApiTarget"] = 34
}

/**
 * アプリバージョン文字列の抽出
 */
tasks.register("pickVersionName") {
    doLast {
        // ファイル読み込み
        val text = project.rootProject.file("build.properties").readText()

        // アプリバージョン文字列の抽出
        val match = Regex("""version_name=(\d[\d\.]{0,}\d)""").find(text)

        // 終了表示
        println(match?.groupValues?.get(1) ?: "unknown")
    }
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
        val file = project.rootProject.file("build.properties")
        file.readText()
            .replace(Regex("""(version_code=)\d+"""), "$1$versionCode")
            .replace(Regex("""(version_name=)\d[\d\.]{0,}\d"""), "$1$versionName")
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