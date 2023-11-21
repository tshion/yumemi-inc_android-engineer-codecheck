# app (Android アプリモジュール)
ストアへリリースするアプリを実装するモジュール。
このモジュールを起点に、種々の機能を組み合わせて、アプリを形作っていきます。



## 開発メモ
### ビルドタイプによる実装の違い
| | debug<br />(開発作業用) | release<br />(ストアリリース用) | 補足
--- | :---: | :---: | ---
Firebase Crashlytics | - | 利用可能 |  |
google-services.json | - | 必須 |  |
[Hyperion] | 利用可能<br />(※Android 13 以下) | - | アプリ起動後に、通知権限を許可した状態でOS 通知欄をタップするか、端末を振るとメニューが出てきます
[LeakCanary](https://square.github.io/leakcanary/) | 利用可能 | - | アプリ起動後にメモリリークが起きると通知されます
OkHttp の通信ログ | 利用可能 | - | アプリ起動後にAndroid Studio の"Logcat" ウィンドウを確認してください
StrictMode | 適用済み | - | 設定内容は[DebugApplication](./src/debug/kotlin/jp.co.yumemi.android.code_check/DebugApplication.kt) を参照してください
Timber | 全て出力 | `e()` をCrashlytics へ転送 |  |
アプリID | 末尾に`.debug` がつく | - |  |
アプリ名 | 先頭に`[D]` がつく | - |  |
アプリバージョン | 末尾に`.debug` がつく | - |  |
カスタムApplication クラス | `DebugApplication` | `ReleaseApplication` |  |
署名情報(release.jks) | - | 必須 |  |

### リリースビルドを試したい
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



## 関連リンク
### 依存ライブラリ ([build.gradle](./build.gradle))
* コア
    * AndroidX
        * [appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat) ([Maven Google](https://mvnrepository.com/artifact/androidx.appcompat/appcompat))
        * [constraintlayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) ([Maven Google](https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout))
        * [core-ktx](https://developer.android.com/jetpack/androidx/releases/core) ([Maven Google](https://mvnrepository.com/artifact/androidx.core/core-ktx))
        * [lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
            * runtime-ktx ([Maven Google](https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-runtime-ktx))
            * viewmodel-ktx ([Maven Google](https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx))
        * [navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
            * fragment-ktx ([Maven Google](https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx))
            * ui-ktx ([Maven Google](https://mvnrepository.com/artifact/androidx.navigation/navigation-ui-ktx))
        * [recyclerview](https://developer.android.com/jetpack/androidx/releases/recyclerview) ([Maven Google](https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview))
    * [COIL](https://github.com/coil-kt/coil) ([Maven Central](https://mvnrepository.com/artifact/io.coil-kt/coil))
    * Firebase BOM ([Maven Google](https://mvnrepository.com/artifact/com.google.firebase/firebase-bom))
        * [Firebase Crashlytics SDK](https://github.com/firebase/firebase-android-sdk/tree/master/firebase-crashlytics)
    * Kotlin Coroutines BOM ([Maven Central](https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-bom))
        * [Kotlinx Coroutines Android](https://github.com/Kotlin/kotlinx.coroutines/tree/master/ui/kotlinx-coroutines-android)
    * ~~Ktor~~
    * [Material Design](https://github.com/material-components/material-components-android) ([Maven Google](https://mvnrepository.com/artifact/com.google.android.material/material))
    * OkHttp BOM ([Maven Central](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp-bom))
        * [OkHttp](https://github.com/square/okhttp)
    * [Timber](https://github.com/JakeWharton/timber) ([Maven Central](https://mvnrepository.com/artifact/com.jakewharton.timber/timber))
* 開発設定
    * [AutoService](https://github.com/google/auto/tree/main/service) ([Maven Central](https://mvnrepository.com/artifact/com.google.auto.service/auto-service))
    * [Hyperion]
        * Hyperion-Attr ([Maven Central](https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-attr))
        * [Hyperion-Core](https://github.com/willowtreeapps/Hyperion-Android/tree/develop/hyperion-core) ([Maven Central](https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-core))
        * Hyperion-Measurement ([Maven Central](https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-measurement))
        * Hyperion-Plugin ([Maven Central](https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-plugin))
        * [Hyperion-Timber](https://github.com/willowtreeapps/Hyperion-Android/tree/develop/hyperion-timber) ([Maven Central](https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-timber))
    * [LeakCanary](https://github.com/square/leakcanary) ([Maven Central](https://mvnrepository.com/artifact/com.squareup.leakcanary/leakcanary-android))
    * OkHttp BOM
        * [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
* テスト
    * AndroidX
        * [test](https://developer.android.com/jetpack/androidx/releases/test)
            * espresso-contrib ([Maven Google](https://mvnrepository.com/artifact/androidx.test.espresso/espresso-contrib))
            * espresso-core ([Maven Google](https://mvnrepository.com/artifact/androidx.test.espresso/espresso-core))
            * junit-ktx ([Maven Google](https://mvnrepository.com/artifact/androidx.test.ext/junit-ktx))
        * [uiautomator](https://developer.android.com/jetpack/androidx/releases/test-uiautomator) ([Maven Google](https://mvnrepository.com/artifact/androidx.test.uiautomator/uiautomator))
    * [JUnit4](https://github.com/junit-team/junit4) ([Maven Central](https://mvnrepository.com/artifact/junit/junit))



[Hyperion]: https://github.com/willowtreeapps/Hyperion-Android
