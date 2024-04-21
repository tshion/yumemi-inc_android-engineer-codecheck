# .github/scripts/
GitHub Actions 等で使うスクリプト実装の置き場。

パス | 用途
--- | ---
get-version-name/ | バージョン名の取得
set-version/ | バージョン情報の設定



## 備考
* `**/test.sh` は簡易的なテストが記述されている
* 下記Pull Request でGradle とJShell を比較したところ、JShell の方が高速な傾向があったため採用した
    * https://github.com/tshion/yumemi-inc_android-engineer-codecheck/pull/169
