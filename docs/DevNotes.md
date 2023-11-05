# 開発メモ
## 適用されている実装 & 設計パターン
* Jetpack
    * [Navigation](https://developer.android.com/guide/navigation)
        * Single Activity, Many Fragments
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)



## ライブラリ依存関係
* AndroidX(Jetpack)
    * https://mvnrepository.com/artifact/androidx.appcompat/appcompat
    * https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout
    * https://mvnrepository.com/artifact/androidx.core/core-ktx
    * espresso
        * https://mvnrepository.com/artifact/androidx.test.espresso/espresso-contrib
        * https://mvnrepository.com/artifact/androidx.test.espresso/espresso-core
    * lifecycle
        * https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-livedata-ktx
        * https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-runtime-ktx
        * https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx
    * navigation
        * https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx
        * https://mvnrepository.com/artifact/androidx.navigation/navigation-ui-ktx
    * https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview
    * https://mvnrepository.com/artifact/androidx.test.ext/junit-ktx
    * https://mvnrepository.com/artifact/androidx.test.uiautomator/uiautomator
* Coil
    * https://mvnrepository.com/artifact/io.coil-kt/coil
* Firebase
    * https://mvnrepository.com/artifact/com.google.firebase/firebase-bom
    * https://mvnrepository.com/artifact/com.google.firebase/firebase-crashlytics-gradle
* https://mvnrepository.com/artifact/com.google.gms.google-services/com.google.gms.google-services.gradle.plugin
* JUnit
    * https://mvnrepository.com/artifact/junit/junit
* Kotlin Coroutines
    * https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
* Ktor
    * https://mvnrepository.com/artifact/io.ktor/ktor-client-android
* LeakCanary
    * https://mvnrepository.com/artifact/com.squareup.leakcanary/leakcanary-android
* Material Design
    * https://mvnrepository.com/artifact/com.google.android.material/material
* Timber
    * https://mvnrepository.com/artifact/com.jakewharton.timber/timber



## リリースビルドを試したい
プロジェクトルートにパスを移動してから下記の手順を踏んで環境を整備してください。

※機密情報のためGit 管理から外しています。なので都度対応してください。

1. release.jks を新規作成し、署名情報を記載してください
1. keystore.properties を新規作成し、下記のフォーマットで必要な情報を記載してください
    ``` gradle
    KEYSTORE_PASSWORD=???
    KEY_ALIAS=???
    KEY_PASSWORD=???
    ```
1. リリースビルドを試してください



## 雑記
* Ktor
    * `HttpClient` は重たい処理なので、インスタンス生成後はなるべく使いまわしたほうが良い
        > Note that creating HttpClient is not a cheap operation, and it's better to reuse its instance in the case of multiple requests. ([※引用元](https://ktor.io/docs/create-client.html#close-client))
* Single Activity, Many Fragments
    * Jetpack Navigation が登場した当時は、Navigation でActivity を取り扱えなかったため
    * ただ下記のような話題もある
        * [Single Activity: Why, when, and how (Android Dev Summit '18)](https://www.youtube.com/watch?v=2k8x8V77CrU)
