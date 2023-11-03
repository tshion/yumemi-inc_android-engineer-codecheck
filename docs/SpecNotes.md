# 仕様メモ
## 画面一覧
画面名 | クラス名
--- | ---
GitHub リポジトリの検索画面 | [OneFragment](../app/src/main/kotlin/jp/co/yumemi/android/code_check/OneFragment.kt)
GitHub リポジトリの詳細画面 | [TwoFragment](../app/src/main/kotlin/jp/co/yumemi/android/code_check/TwoFragment.kt)
(アプリ起動後の画面ホスト) | [TopActivity](../app/src/main/kotlin/jp/co/yumemi/android/code_check/topActivity.kt)



## アプリの使い方
### 動作
> 1. 何かしらのキーワードを入力
> 2. GitHub API（`search/repositories`）でリポジトリを検索し、結果一覧を概要（リポジトリ名）で表示
> 3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

### 大まかな処理の流れ
![UseFlow](https://www.plantuml.com/plantuml/svg/VLJVIzjG57w_VyMXmfWFhU-5YXqu7MnCPhRtsDxfXPg4v5QtjoNPcCx1L3OAhYejRdE3TWEL3kFmZxbDqluNk_bXUsygXX32FT_vpdVETv8vYwechQ_Na5gXkWdFBMoY5VqLvCg6aOEfnsHrZJwerJ42VXaSIoh4K3KAYcwKAvHi4Ffw8lPKmmzDTNKTQ_Iss0k2Dv_eLLoR9DtKHubOgm9NWiHuRe7GnVeAF9jVLg2yz2W7gWN96KAeL0Bcj9dROEwlmKNFt_d2N7vtc7i5fHAAUeFyhAWSYd0_apADn3Y72yr4S7_hC6WUXv-zuM6RsTt1QSVl7amZ4PNdMN6n8WGdpV2yFKQtJiEJoqHg4hfLeLpo1lYRevYxpMdyTz_y3r_XNXhokyU3riV8GRAXKYoDNz8rAqrVe6unvnzptPHzxuhPP_xxIsOt9DAnRn8DfqrjASAYeYmjm_3DGV-w5L5bbALMQJf_DwFL0ltbpM6q2mlp2inOM3KhQpCcDdIByAKas8gN8Sf2QQNyP2z9qV1YDpXgeYhErfGboqCMuTa-xt0rBZLsLIOPSQD2eO3kj7P4rF_ROVOESxRxdXUy_SHFcEF4pttcNCS6DRAE2fzCbj3P3teUixUO_JtPmA7T2tzuI4mHkXhyF0lF_qpiwXoVFVz1_0S0 "UseFlow")



## その他
* GitHub REST API
    * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
    * [本リポジトリで利用する箇所を抜粋したOpenAPI 定義書](./github.yaml)
* アプリバージョンの仕様
    * https://github.com/tshion/yumemi-inc_android-engineer-codecheck/issues/5 を参照してください
