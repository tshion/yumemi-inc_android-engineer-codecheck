# 仕様メモ
## アプリの使い方
> 1. 何かしらのキーワードを入力
> 2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
> 3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

![UseFlow](https://www.plantuml.com/plantuml/svg/XL9HIy9G67s_l-B38Ef1VHSCYqI37YJj1qoxwS2siLsZnxOLP0L5545PePXMe0KbIWlyCLVT_1VTESrfKcECoxVpdU-SxzoGGZYPv7QoY4iHKOPD1SieAUw1PrcIF32tpgSpP2MRmmZO8p4id-8bJY1WDivxzuLEQz-gLCrBetzN0aw1DM4BxyMuz6-yzVnkVhoDaQkOS7ns0CN25h2Po0ttH7WIpILX8nnFm79ipMFtE3M4K308L2jH_PhgBrQZsJsveZfxQrJ_Wc0GsIx0kpHM0m5Oc6fOj1tpknp1Bj4o9ZbPcE6DgdMh-6IMFvtXlNB1_2ZzEtC4UsKOgkSPIVVmiNjy2_F3NztwWrKydQK4ZNaOwN0P0OWc4h4uzFTlEksYJJVLFroG8BApR4S7ulQeQ0SI2IV0hs1EJcNyCfP4XMUXyrWPH61tZJRWJ73sCAevafsQQny-dm_zuVu7tsdLg7f2jNp7C7e7Pwn2DMtmlQ1Qc-ewIsVIzVYAJBDf5xscGTKZgbQSVFfgqwmQoDNXPGG3LO69g-SI7cAcsSt_1W00 "UseFlow")



## サーバー関連
* GitHub REST API
    * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
    * [本リポジトリで利用する箇所を抜粋したOpenAPI 定義書](./github.yaml)



## アプリ運用関連
* アプリ公開環境
    * GitHub Releases
* アプリバージョン
    * [Issue #5](https://github.com/tshion/yumemi-inc_android-engineer-codecheck/issues/5) を参照してください
