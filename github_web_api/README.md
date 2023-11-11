# github_web_api モジュール
GitHub REST API とHTTP 通信をするモジュール。



## 開発メモ
* GitHub REST API の仕様が変わった場合、本モジュールも追従します
* Kotlin Explicit API モードがstrict レベルで有効となっています
* [OpenAPI 定義](../docs/github.yaml) から生成したコードを、編集した上で流用しています
    * `Array` -> `List` への型変更
    * 型の前についている`kotlin.` を削除
    * コメントの整形
* エンドポイントに紐づくメソッド名は、`HTTP 動詞` + `パス` というふうに付けています



## 関連リンク
* GitHub REST API
    * エンドポイント
        * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
    * メタ情報
        * [API のバージョン - GitHub Docs](https://docs.github.com/ja/rest/overview/api-versions?apiVersion=2022-11-28)
        * [メディアの種類 - GitHub Docs](https://docs.github.com/ja/rest/overview/media-types?apiVersion=2022-11-28)
