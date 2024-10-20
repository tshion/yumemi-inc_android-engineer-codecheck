## Let's contributing!
これからいろいろ書いていきますが、一番大事なのは気軽にやってみることです。
皆さんのチャレンジを、首を長くしてお待ちしております:bow:



## 開発環境について
下記のツールをセットアップ後に、
このプロジェクトのルートディレクトリーを
Android Studio で開くことで開発作業をする環境が整います。

もし何か分からないことが出てきたら、声をかけてください。

* macOS or Windows
* [Android Studio](https://developer.android.com/studio): Hedgehog | 2023.1.1
    * Gradle: 8.0 ([gradle-wrapper.properties](../gradle/wrapper/gradle-wrapper.properties))
    * JDK: 17
    * Kotlin Plugin: 1.9.21 以降 ([トップレベルのbuild.gradle](../build.gradle.kts))
* GitHub

### 任意
下記のツールは無くても開発できますが、あると便利なので、興味に応じて試してみてください。

* [JetBrains Toolbox App](https://www.jetbrains.com/ja-jp/toolbox-app/)
    * Android Studio の複数バージョンの併用
    * プロジェクトと起動IDE の紐付け
* [Visual Studio Code](https://code.visualstudio.com/)
    * .gitignore の記述
    * GitHub Actions の構築
    * Git クライアント + Pull Request 等の確認
    * Markdown の記述
    * [OpenAPI](https://www.openapis.org/) の記述
    * [PlantUML](https://plantuml.com/) の記述



## 開発作業の流れ
### 基本
1. タスクはGitHub Issue で管理されているので、その中からやってみたいものを探す
1. やりたいものが見つかったらIssue のAssignees に自分を設定する
1. 最新の`develop` ブランチからGit ブランチを切る
    * 名前は`feature/???` という形式で、`???` の部分を良い感じに設定してください
1. 開発する
1. 1日の終わりに出来たところまでをDraft Pull Request として作成する
    * 特に理由が無ければ`develop` ブランチに対してPull Request を作ってください
    * 進捗管理の都合上、どこまで出来ているかを見たいので、対応してくれるととても助かります
1. 作成したPull Request の`Development` に関連のGitHub Issue を紐づける
    * Pull Request のマージ + 条件を満たすとIssue もクローズしてくれるようになります
1. 作業が完了したら、Draft 状態を解除し、レビュー担当者に連絡してください

### 緊急対応すべきと宣言されたバグフィックス
「緊急」とあるように、綿密な進捗管理が必要となるものなので、適宜状況共有してくれると助かります。

1. 最新の`released` ブランチからGit ブランチを切る
    * 名前は`hotfix/???` という形式で、`???` の部分を良い感じに設定してください
1. バグ修正をする
1. レビュー担当者に連絡してください



## レビュー方針
* レビューする際、PR 単位で確認します
    * コミット毎ではないので、ある程度は雑で大丈夫です
    * (Revert するケースを考えるとコミットが綺麗だと嬉しいですが、開発スピードを優先したいので、ここは後回しにします)
* API の誤用と思われる場合は差し戻します
* マージコンフリクトした場合は、下記の順で判定します
    1. 実装の都合
    1. アルファベット順



## リリース作業の流れ
### 通常
1. リリース対象Pull Request が`develop` ブランチに全てマージされていることを確認する
1. https://github.com/tshion/yumemi-inc_android-engineer-codecheck/actions/workflows/140-create-version-pr.yml のGitHub Actions を実行し、アプリバージョンの更新Pull Request を作成する
1. 前の手順で作成したPull Request に問題がなければマージする
1. `released` ブランチへのマージPull Request が作成されるはずなので、しばらく待つ
    * 手動実行する場合は、下記に移動してください
        * https://github.com/tshion/yumemi-inc_android-engineer-codecheck/actions/workflows/160-create-release-pr.yml
1. 前の手順で作成したPull Request に問題がなければマージする
1. GitHub Releases が自動生成されるので、様子を見る

### hotfix
1. hotfix のPull Request に問題ないことを確認する
1. 上記のブランチを指定して、https://github.com/tshion/yumemi-inc_android-engineer-codecheck/actions/workflows/140-create-version-pr.yml を実行し、アプリバージョンの更新Pull Request を作成する
1. 前の手順で作成したPull Request に問題がなければマージする
1. 作業が完了したら下記ブランチそれぞれに対してPull Request を作成する
    * `released` ブランチ -> hotfix のブランチを指定して https://github.com/tshion/yumemi-inc_android-engineer-codecheck/actions/workflows/160-create-release-pr.yml を実行する
    * `develop` ブランチ -> 例: [PR #52](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/pull/52)
1. 前の手順で作成したPull Request に問題がなければマージする
1. GitHub Releases が自動生成されるので、様子を見る



## 備考
### Git ブランチの役割について
ブランチ名 | 役割
--- | ---
develop | 開発作業のメインブランチ
feature/??? | 各Issue の作業ブランチ
hotfix/??? | 緊急で対応しないといけないバグフィックスの作業ブランチ
released | リリースの記録ブランチ

### GitHub のLabels について
どのLabel にするかは各自で判断が別れやすいところなので、下記以外は何もつけなくて大丈夫です。

* `bug` -> hotfix なものに付与する
    * 作業進行で慎重に扱わないといけないため
* `duplicate` -> 重複しているものに付与し、他のところで取り扱うことを宣言する
* `github` -> GitHub 機能を利用しているものに付与する
    * 利用サービスによって変動するので、別プロジェクトとの差分を視覚化するためにマーキングする

### GitHub のMilestones について
* 基本的にはGitHub のIssue を紐づけます
* もしGitHub のPull Request のみしか存在しない場合は、それを紐づけます
    * 例: ドキュメント更新のPull Request

### 開発の心得
* クラスやメソッドは、なるべく統治しやすい単位で切ること
    * ユニットテストを書きやすいかどうかが一つの目安になります
    * 今後の保守で機能が無くなったり、実装の書き方が古すぎる場合、この単位で削除や差し替えのための再実装が容易であるとサイコーです:laughing:
* 参考にした資料などの記録を、Issue やPR に忘れずに記載してください
* 変更はなるべく小さくすること
* メソッド名は基本的に```(動詞) + (名詞)``` で付けていくこと<br />
    * 何をどうしたいかがパッと見でわかると超クールw

### メモを残したくなったら
本リポジトリでは下記を用意しているので、よさげなところがあればそこに追記してください。

* 開発にまつわることを書きたい
    * [設計メモ](./Architecture.md)
    * 各モジュールのREADME
* 仕様にまつわることを書きたい → [仕様メモ](./SpecNotes.md)

それ以外に何かあれば、`docs/` 配下で作業し、Pull Request を出してください。

### ルールオブスリー(３度目の法則)
作業を進めていくと似たようなコードを見つけてしまい、ついつい共通化とか考えたくなるとは思いますが、一旦心を落ち着けて、下記を意識してもらえると嬉しいです。

> 最初は、単純に作業を行います。
> ２度目は以前と似たようなことをしていると気づいた場合には、重複や無駄を意識しつつも、とにかく作業を続けてかまいません。
> そして３度目に同じようなことをしていると気づいたならば、そこでリファクタリングをするのです。

引用: Martin Fowler (2014) 「新装版　リファクタリング　既存のコードを安全に改善する」　児玉公信・友野晶夫・平澤章・梅澤真史訳、オーム社



## 参考文献
* CONTRIBUTING の内容の参考
    * https://github.com/DroidKaigi/conference-app-2023/blob/main/CONTRIBUTING.ja.md
    * https://github.com/tshion/XAppRecipe/blob/develop/CONTRIBUTING.md
