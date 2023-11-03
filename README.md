# GitHub リポジトリ検索アプリ(お題作成: 株式会社ゆめみさん)
## はじめに
* **本リポジトリは[yumemi-inc/android-engineer-codecheck] をベースに、アレンジを加えたものとなります**
* 個人的な勉強が目的のため、追加で下記の状況を設定しました
    * アプリは他のIT 企業によってリリースされているが、大人の事情で、私が所属する企業がアプリを引き継ぐことになった
    * アプリの発注元はプロダクト改良に積極的で、技術面の改良は我々に一任されており、さらに予算もついている
        * 補足: もし消極的だった場合、予算と睨めっこしながらバグフィックスをこなす話となってしまうので、本リポジトリではその制限を設けない
    * 前述の条件のため、アプリの改良はチームを組んで対応することになったので、タスク管理を行う必要がある
* アプリ改良のマイルストーンは下記のように設定しました
    * [v1.0.0](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/milestone/1) -> 他のIT 企業がアプリをリリースした状況の再現
    * [v1.1.0](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/milestone/3) -> 本格的なアプリ改良に入る前の下準備
    * [v1.2.0](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/milestone/2) -> 本格的なアプリ改良 (**複製元で定義されている課題への対応**)



## アプリについて
GitHub リポジトリを検索できるAndroid アプリ。

<img src="docs/app.gif" width="320" />

### インストール方法
アプリのapk は[GitHub リポジトリのリリース](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/releases) 経由で入手することが出来ます。
Android OS 6.0 以降の端末で、apk のダウンロード → インストールをお試しくださいませ。



## もしアプリ開発をやってみたくなったら
下記を参照してください。

* [仕様メモ](./docs/SpecNotes.md)
    * 画面一覧
    * アプリの使い方
    * その他
* [CONTRIBUTING](./CONTRIBUTING.md)
    * 開発環境について
    * Git ブランチについて
    * 開発作業の流れ
* [開発メモ](./docs/DevNotes.md)
    * 適用されている設計パターン
    * ライブラリ依存関係
    * リリースビルドを試したい
    * 雑記



## 関連リンク
* 複製元リポジトリ: [yumemi-inc/android-engineer-codecheck]
    * [複製時点のREADME](./docs/README.original.md)



[yumemi-inc/android-engineer-codecheck]: https://github.com/yumemi-inc/android-engineer-codecheck/tree/06e32c7fe9879ad35d4b8e02688169fc805f30f0
