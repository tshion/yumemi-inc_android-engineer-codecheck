# GitHub リポジトリ検索アプリ(お題作成: 株式会社ゆめみさん)
## はじめに
* **本リポジトリは[yumemi-inc/android-engineer-codecheck] をベースに、アレンジを加えたものとなります**
* アプリ改良のマイルストーン
    * [完了](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/milestones?direction=asc&sort=title&state=closed)
    * [進行中](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/milestones?direction=asc&sort=title&state=open)

<details>
<summary>※個人的な勉強が目的のため、状況設定を追加しました</summary>

* アプリは他のIT 企業によってリリースされているが、大人の事情で、私が所属する企業がアプリを引き継ぐことになった
* アプリの発注元はプロダクト改良に積極的で、技術面の改良は我々に一任されており、さらに予算もついている
    * 補足: もし消極的だった場合、予算と睨めっこしながらバグフィックスをこなす話となってしまうので、本リポジトリではその制限を設けない
* 前述の条件のため、アプリの改良はチームを組んで対応することになったので、タスク管理を行う必要がある
</details>



## アプリについて
GitHub リポジトリを検索できるAndroid アプリ。

~ 1.1.x | 1.2.x
:---: | :---:
<img src="docs/app.gif" width="320" /> | <img src="docs/app1_2_x.gif" width="320" />

### インストール方法
アプリのapk は[GitHub リポジトリのリリース](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/releases) 経由で入手することが出来ます。

* Android
    * Android OS 6.0 以降の端末で、apk のダウンロード → インストールをお試しくださいませ
* <details>
  <summary>(開発者向けの実験的機能) <a href="https://learn.microsoft.com/ja-jp/windows/android/wsa/">Windows Subsystem for Android</a></summary>

  1. apk をダウンロードする
  1. [Windows 11 Android SubsystemにAPKをインストールする方法 – SMART ASW](https://smartasw.com/archives/13868) を参考に、 `adb` 経由でインストールする
  </details>



## もしアプリ開発をやってみたくなったら
下記を参照してください。

* [CONTRIBUTING](./docs/CONTRIBUTING.md)
    * 開発環境について
    * 開発作業の流れ
    * レビュー方針
    * リリースの流れ
    * 備考
* [先方からの要求事項](./docs/Requirements.md)
    * アプリの使い方
    * システム関連
* [仕様メモ](./docs/SpecNotes.md)
    * アプリ関連
        * 画面構成とデザイン
        * データの取り扱い方針
        * バージョニング
        * ログの取り扱い方針
    * 外部システム関連
        * GitHub REST API
        * クラッシュ分析ツール
    * 備考
        * データセーフティ
* [設計メモ](./docs/Architecture.md)
    * 基本方針
    * 適用する設計パターン
    * 設計パターンを支えるライブラリ
* 各モジュールのREADME
    * [app](./app/)
        * リリースビルドを試したい
        * 依存ライブラリ
    * [app_core](./app_core/)
    * [github_web_api](./github_web_api/)



## 関連リンク
* 複製元リポジトリ: [yumemi-inc/android-engineer-codecheck]
    * [複製時点のREADME](./docs/README.original.md)



[yumemi-inc/android-engineer-codecheck]: https://github.com/yumemi-inc/android-engineer-codecheck/tree/06e32c7fe9879ad35d4b8e02688169fc805f30f0
