# .github/
GitHub 用の設定や関連する実装などの置き場。

パス | 用途
--- | ---
[actions/apply-gh-actions-bot-identity/](./actions/apply-gh-actions-bot-identity/) | Git User にGitHub Actions Bot の情報を適用する
[actions/setup-java-runtime/](./actions/setup-java-runtime/) | Java 実行環境のセットアップ
[scripts/get-version-name/](./scripts/get-version-name/) | バージョン名の取得
[scripts/set-version/](./scripts/set-version/) | バージョン情報の設定
[workflows/140-create-version-pr.yml](./workflows/140-create-version-pr.yml) | バージョン情報を更新するPull Request 作成
[workflows/160-create-release-pr.yml](./workflows/160-create-release-pr.yml) | リリース候補の変更内容をまとめたPull Request 作成
[workflows/180-deploy.yml](./workflows/180-deploy.yml) | リリース(デプロイ)
[issue_template.md](./issue_template.md) | Issue 作成時に適用されるテンプレート
[pull_request_template.md](./pull_request_template.md) | Pull Request 作成時に適用されるテンプレート



## 備考
* 本ドキュメントのファイル名が `READ_ME.md` の理由
    * 本リポジトリのWEB サイトに表示される `README.md` を調整するため
    * 参考: [READMEについて - GitHub Docs]
        > リポジトリに複数の README ファイルが含まれている場合、表示されるファイルは、.github ディレクトリ、リポジトリのルート ディレクトリ、最後に docs ディレクトリの順に選択されます。
* scripts/
    * JShell を採用した理由
        * Android のビルド環境があれば、追加で他のセットアップをしなくても、利用出来るため
        * 上記の条件であればGradle も当てはまるが、[Pull Request #169](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/pull/169) で比較したところ、JShell の方が高速な傾向が見られたため
    * `**/test.sh` は簡易的なテストが記述されている
    * VSCode タスクとして実行できるようになっているので、必要に応じて試すこと
* workflows/
    * プレフィックスにある数値は、表示がリリースフローの作業順になるようにつけている
        * 大まかな意味は下記となる
            * 0xx -> リリース前作業
            * 1xx -> リリース(デプロイ) 作業
        * この後の改修で順番が変わることもあるので、間隔を空けて数値を振っている



## 参考文献
* GitHub Actions
    * [コンカレンシーの使用 - GitHub Docs](https://docs.github.com/ja/actions/using-jobs/using-concurrency)
    * [コンテキスト - GitHub Docs](https://docs.github.com/ja/actions/learn-github-actions/contexts)
    * [式 - GitHub Docs](https://docs.github.com/ja/actions/learn-github-actions/expressions)
    * トリガー
        * [ワークフローのトリガー - GitHub Docs](https://docs.github.com/ja/actions/using-workflows/triggering-a-workflow)
        * [ワークフローをトリガーするイベント - GitHub Docs](https://docs.github.com/ja/actions/using-workflows/events-that-trigger-workflows)
    * [複合アクションを作成する - GitHub Docs](https://docs.github.com/ja/actions/creating-actions/creating-a-composite-action)
* GitHub CLI
    * [Manual | GitHub CLI](https://cli.github.com/manual/)
    * [GitHub REST API に関するドキュメント - GitHub Docs](https://docs.github.com/ja/rest?apiVersion=2022-11-28)
* [Java Shellユーザー・ガイド](https://docs.oracle.com/javase/jp/17/jshell/toc.htm)
* [READMEについて - GitHub Docs]



[READMEについて - GitHub Docs]: https://docs.github.com/ja/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes
