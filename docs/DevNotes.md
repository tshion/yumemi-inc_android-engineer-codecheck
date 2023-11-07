# 開発Tips
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
リリースビルドを行うためには、下記の機密情報が必要です。
Git 管理から外しているため、試す際は追加でセットアップを行なってください。

* google-services.json -> Firebase を使う際の情報
* release.jks -> 署名情報

セットアップ手順は下記となります。
なお手順の中に出てくるファイルパスは、プロジェクトルートを基準にしたものとなります。

1. google-services.json を準備し、`./app/google-services.json` に配置する
    * ~~まずはプロジェクト管理者に問い合わせてください~~
    * 諸般の事情で共有されない場合は、Firebase プロジェクトを新規作成してください
        * application id は、`./app/build.gradle` に記載のものと揃えてください
1. 署名情報を新規作成し、`./release.jks` に配置する
    * ストアへリリースするわけではないので、各自で作成してください
1. `./keystore.properties` を新規作成し、下記のフォーマットで必要な情報を記載する
    ``` gradle
    KEYSTORE_PASSWORD=???
    KEY_ALIAS=???
    KEY_PASSWORD=???
    ```
1. リリースビルドを試す



## 雑記
* Ktor
    * `HttpClient` は重たい処理なので、インスタンス生成後はなるべく使いまわしたほうが良い
        > Note that creating HttpClient is not a cheap operation, and it's better to reuse its instance in the case of multiple requests. ([※引用元](https://ktor.io/docs/create-client.html#close-client))
* Single Activity, Many Fragments
    * Jetpack Navigation が登場した当時は、Navigation でActivity を取り扱えなかったため
    * ただ下記のような話題もある
        * [Single Activity: Why, when, and how (Android Dev Summit '18)](https://www.youtube.com/watch?v=2k8x8V77CrU)
