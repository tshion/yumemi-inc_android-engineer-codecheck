# 仕様メモ
## アプリ関連
### 画面構成とデザイン
画面名 | 概要
--- | ---
検索画面 | GitHub リポジトリの検索条件の入力と、その結果を表示する画面
詳細画面 | 選択したGitHub リポジトリの詳細情報を表示する画面
スプラッシュ画面 | アプリ起動時に表示し、起動処理を行う画面

[デザイン関連をまとめたGoogle スライド](https://docs.google.com/presentation/d/1MhPFy1jIEENGLCKthz4CfsVcAPls7aGKYyU_Ky1TQno/edit?usp=sharing) も参照してください。

### データの取り扱い方針
* アプリ側で永続化すべきデータは、システム上は特になし
    * なので基本的にはオンメモリで完結させるようにする
* センシティブな情報は、本システムでは特になし
* データの検証は、外部システム仕様を完全に再現するのは難しいため、あまりにも極端なものを排除するくらいのレベルにとどめる

### バージョニング
セマンティックバージョニングをベースに、下記のようなルールを設定します。

項目 | 桁数
--- | :---:
major | 1以上
minor | 2
patch | 2

計算式は下記とします。
* `versionCode` -> `(10,000 * major) + (100 * minor) + patch`
* `versionName` -> `major.minor.patch`

### ログの取り扱い方針
* システムが要求するログ出力は特になし
* セキュリティ等の観点から、なるべくログ出力を避ける
    * 開発目的ならば、代わりにブレイクポイントを置いてデバッグするなどの手法に切り替える



## 外部システム関連
### GitHub REST API
[本リポジトリで利用する箇所を抜粋したOpenAPI 定義書](./github.yaml) に記載した下記のエンドポイントを利用しています。

* https://docs.github.com/rest/releases/releases#get-the-latest-release
* https://docs.github.com/rest/search/search#search-repositories

### クラッシュ分析ツール
[Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) を利用しています。



## 備考
### データセーフティ
* Firebase
    * [Crashlytics](https://firebase.google.com/docs/android/play-data-disclosure#crashlytics)
