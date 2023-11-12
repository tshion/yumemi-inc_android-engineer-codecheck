# github_web_api (Kotlin モジュール)
GitHub REST API とHTTP 通信をするモジュール。

## 使い方
### 利用側で準備すること
* OkHttpClient のインスタンス
    * [OkHttpClient のAPI ドキュメント](https://square.github.io/okhttp/5.x/okhttp/okhttp3/-ok-http-client/index.html)
      に `OkHttpClients Should Be Shared` とあるため
* 通信キャッシュの保存先のディレクトリ
    * [OkHttp のデフォルトではOFF](https://square.github.io/okhttp/features/caching/)
      ですが、[HTTP Status Code 304](https://developer.mozilla.org/ja/docs/Web/HTTP/Status/304)
      の記述があり、アプリ側でキャッシュの考慮が必要なため

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
    * リポジトリ検索
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
    * [REST API を使用するためのベスト プラクティス - GitHub Docs](https://docs.github.com/ja/rest/guides/best-practices-for-using-the-rest-api?apiVersion=2022-11-28#follow-any-redirects-that-the-api-sends-you)
      の記載で、意図的に無視すると不正利用と見なされる場合がありそうなため
* もしProguard が必要な場合は、利用側で調整してください



## 開発メモ
* GitHub REST API の仕様が変わった場合、本モジュールも追従します
* Kotlin Explicit API モードがstrict レベルで有効となっています
* [OpenAPI 定義](../docs/github.yaml) から生成したコードを、編集した上で流用しています
    * IDE の検索機能で、`([a-z])([A-Z])` を`$1_\l$2` で置換し、変数名をスネークケース化
    * 型の前についている`kotlin.` を削除
    * 型変更
        * `Array` -> `List`
        * `BigDecimal` -> `Int`
        * nullable
            * 必須のものはnullable 化
            * 任意なものはnullable + 既定値`null` を割り当て
    * コメントの整形
* エンドポイントに紐づくメソッド名は、`HTTP 動詞` + `パス` というふうに付けています



## 関連リンク
* GitHub REST API
    * エンドポイント
        * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
    * メタ情報
        * [API のバージョン - GitHub Docs](https://docs.github.com/ja/rest/overview/api-versions?apiVersion=2022-11-28)
        * [メディアの種類 - GitHub Docs](https://docs.github.com/ja/rest/overview/media-types?apiVersion=2022-11-28)
