# app モジュール
ストアへリリースするアプリを管理するモジュール。
ここを起点に、種々の機能を組み合わせて、アプリを実装していきます。



## 開発メモ
### ビルドタイプによる実装の違い
| | debug<br />(開発作業用) | release<br />(ストアリリース用)
--- | :---: | :---:
Firebase Crashlytics | - | 利用可能
google-services.json | - | 必須
Timber | 全て出力 | `e()` をCrashlytics へ転送
カスタムApplication クラス | `DebugApplication` | `ReleaseApplication`
署名情報(release.jks) | - | 必須