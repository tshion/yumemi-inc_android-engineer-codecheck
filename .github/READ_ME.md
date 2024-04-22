# .github/
GitHub 用の設定や関連する実装などの置き場。

パス | 用途
--- | ---
[actions/](./actions/) | GitHub Actions の共通実装
[scripts/get-version-name/](./scripts/get-version-name/) | バージョン名の取得
[scripts/set-version/](./scripts/set-version/) | バージョン情報の設定
[workflows/](./workflows/) | GitHub Actions のワークフロー定義
issue_template.md | Issue 作成時に適用されるテンプレート
pull_request_template.md | Pull Request 作成時に適用されるテンプレート



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



## 参考文献
* [Java Shellユーザー・ガイド](https://docs.oracle.com/javase/jp/17/jshell/toc.htm)
* [READMEについて - GitHub Docs]



[READMEについて - GitHub Docs]: https://docs.github.com/ja/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes
