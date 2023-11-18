# github_web_api (Kotlin モジュール)
GitHub REST API とHTTP 通信をするモジュール。

## 使い方
### 利用側で準備すること
* OkHttpClient のインスタンス
    * [OkHttpClient のAPI ドキュメント](https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/index.html) に `OkHttpClients Should Be Shared` とあるため
* 通信キャッシュの保存先のディレクトリ
    * [OkHttp のデフォルトではOFF](https://square.github.io/okhttp/features/caching/) ですが、[HTTP Status Code 304](https://developer.mozilla.org/ja/docs/Web/HTTP/Status/304) の記述があり、アプリ側でキャッシュの考慮が必要なため

### 実装の流れ
1. インスタンスを生成する
    ``` kotlin
    val githubWebApi = GitHubWebApi(
        applicationId = "",
        cacheDir = applicationContext.cacheDir,
        client = okhttpClient,
    )
    ```
1. プロパティ`endpoint` 経由で通信を実行する (※Kotlin Coroutines のScope が必要)
    * 例) リポジトリ検索
        ``` kotlin
        try {
            val response = githubWebApi.endpoint.getSearchRepositories("android")
        } catch (e: HttpException) {
            // GitHub REST API との通信で失敗した場合
            val errorResponse = githubWebApi.parse(e)
            // TODO: クラッシュ分析ツールへ連携
        } catch (e: IOException) {
            // 端末オフラインや通信タイムアウトなど
        } catch (e: Exception) {
            // 想定外のエラー
            // TODO: クラッシュ分析ツールへ連携
        }
        ```

### 注意事項
* GitHub REST API からエラーが返ってきた場合は、クラッシュ分析ツールへ連携することを推奨します
    * [REST API を使用するためのベスト プラクティス - GitHub Docs](https://docs.github.com/ja/rest/guides/best-practices-for-using-the-rest-api?apiVersion=2022-11-28#follow-any-redirects-that-the-api-sends-you) の記載で、意図的に無視すると不正利用と見なされる場合がありそうなため
* 通信キャッシュ用の領域は4MB に設定されています
* 通信タイムアウトは10.5 秒に設定されています
* もしProguard が必要な場合は、利用側で調整してください



## 開発メモ
* GitHub REST API の仕様が変わった場合、本モジュールも追従します
* Kotlin Explicit API モードがstrict レベルで有効となっています
* [OpenAPI 定義](../docs/github.yaml) から生成したコードを、編集した上で流用しています
    * IDE の検索機能で、`([a-z])([A-Z])` を`$1_\l$2` で置換し、変数名をsnake case 化
        * Moshi はsnake case をcamel case へ自動変換する機能が無いのでJSON キー名と揃えるため (※[関連stack overflow](https://stackoverflow.com/a/52149637))
        * 本モジュールは外部サービスに強く依存し、アプリで利用するうえで信用できるか不明のため、その注意喚起を含め違和感を持ってもらうため、snake case のままとしています
    * 型の前についている`kotlin.` を削除
    * 型変更
        * `Array` -> `List`
        * `BigDecimal` -> `Int`
        * nullable
            * 必須のものはnullable 化
            * 任意なものはnullable + 既定値`null` を割り当て
    * コメントの整形
* エンドポイントに紐づくメソッド名は、`HTTP 動詞` + `パス` というふうに付けています
* ユニットテスト用のサンプルデータは `test/resources/` にあるJSON ファイルを書き換えることで変更可能です



## 関連リンク
### GitHub REST API
* エンドポイント
    * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
* メタ情報
    * [API のバージョン - GitHub Docs](https://docs.github.com/ja/rest/overview/api-versions?apiVersion=2022-11-28)
    * REST API のリソース - GitHub Docs
        * [Timeouts](https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#timeouts)
        * [User agent の必要性](https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#user-agent-%E3%81%AE%E5%BF%85%E8%A6%81%E6%80%A7)
        * [クライアントエラー](https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#client-errors)
    * [メディアの種類 - GitHub Docs](https://docs.github.com/ja/rest/overview/media-types?apiVersion=2022-11-28)

### 依存ライブラリ ([build.gradle](./build.gradle))
* コア
    * OkHttp BOM ([Maven Central](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp-bom))
        * [OkHttp](https://github.com/square/okhttp)
* モジュール内部利用
    * [Moshi Adapters](https://github.com/square/moshi/tree/master/moshi-adapters) ([Maven Central](https://mvnrepository.com/artifact/com.squareup.moshi/moshi-adapters))
    * [Moshi Kotlin](https://github.com/square/moshi#kotlin) ([Maven Central](https://mvnrepository.com/artifact/com.squareup.moshi/moshi-kotlin))
    * [Retrofit](https://github.com/square/retrofit) ([Maven Central](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit))
    * [Retrofit Converters](https://github.com/square/retrofit/tree/master/retrofit-converters)
        * Converter: Moshi ([Maven Central](https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-moshi))
* テスト
    * [JUnit4](https://github.com/junit-team/junit4) ([Maven Central](https://mvnrepository.com/artifact/junit/junit))
    * Kotlin Coroutines BOM ([Maven Central](https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-bom))
        * [Kotlin Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/README.md)
    * OkHttp BOM
        * [OkHttp MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
    * ([Okio](https://github.com/square/okio))
