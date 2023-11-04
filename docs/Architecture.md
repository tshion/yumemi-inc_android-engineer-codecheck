# 設計メモ
## 基本方針
* 構造面
    * 役割毎にモジュールを分割し、その組み合わせでアプリを構築する
    * 式年遷宮できるように、なるべく置換しやすい単位で実装する
* 何かしらのインスタンスを生成する場合は、システム的に正しい状態になるように実装する



## 適用する設計パターン
基本的にはModel-View-ViewModel(MVVM) ですが、各階層をより細かく管理していくために、下記の概念を追加する。

* Model
    * Entity -> システムのデータ定義
    * Repository Pattern
    * UseCase -> システムでどうデータを取り扱うかの定義
* View
    * Atomic Design
    * Navigation Pattern

![Architecture](https://www.plantuml.com/plantuml/svg/RPAxJiCm58PtFyLHzxu1GaLLkGn8g5mM526JkiKYjQF4fQeGGn84WgKJ7M1XG0AMIeW15H0yZ6bu3Sv9IY-KB77-y_i_dqzS2WqFJAlfCRRa6Xt0TYW2ndpk7lA6W0Bt_G8SCu1TBCiZQJg0fkM0Dvf26GTu23jIjCb0xtLT4vx3wBCgV1rAZEmux6GsSS_LWIZGNbhayHLbCDoX9IAKUIXecz96W2Vg1dGzCsFTnYys7jZap2QtDhed9NaRTdislamldco4-hbDtctyELSwJNk3zFcAzAW_x5z_VpmoeMf0RQOG6z8SjFQHKYb1GybGSp63fMbih9KzIIFufJJQ9yZSXgOLQLRHRwC7x9MHZAY-Nk_IxarEyX-6L4O4TUuYo2YZMjdS0koOSN1VCdn6GuA5OastLN4HnkEVBqr-1MDZs-pEJ9z8-IqO7t9IeExJSil_ar_2W-1r-W40 "Architecture")


色々な設計パターンで整理すると、下記となります。

モジュール | パス | Atomic Design | Clean Architecture | MVVM | 備考
--- | --- | --- | :---: | --- | ---
app | ./ | - | 外 | - | アプリ本体
app | atoms/ | atoms | 外 | V |
app | molecules/ | molecules | 外 | V |
app | organisms/ | organisms | 外 | V |
app | pages/ | pages | 外 | V, VM | 各画面実装
app | repositories/ | - | 外 | M |
app | templates/ | templates | 外 | V |
app_core | entities/ | - | 中心 | M | 取り扱うデータの定義
app_core | usecases/ | - | 中心 | M | 取り扱い方法の定義
github_connector | ./ | - | 外 | M | GitHub とのデータ交換方法の定義



## 実装メモ
* Jetpack
    * [Navigation](https://developer.android.com/guide/navigation)
        * Single Activity, Many Fragments
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines)
