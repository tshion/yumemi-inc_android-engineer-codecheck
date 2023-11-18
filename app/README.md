# app モジュール
ストアへリリースするアプリを管理するモジュール。
ここを起点に、種々の機能を組み合わせて、アプリを実装していきます。



## 開発メモ
### 開発時にアプリ上で使える便利機能
[Hyperion](https://github.com/willowtreeapps/Hyperion-Android) を組み込んだため、
debug ビルドでアプリ実行した際に下記の機能を利用できます。

通知権限を許可した状態でOS 通知欄をタップするか、端末を振ってHyperion メニューを表示してください。

* Timber 出力の確認
* View 間の距離計測
* View の属性確認

### ビルドタイプによる実装の違い
| | debug<br />(開発作業用) | release<br />(ストアリリース用)
--- | :---: | :---:
Firebase Crashlytics | - | 利用可能
google-services.json | - | 必須
LeakCanary | 利用可能 | -
StrictMode | 適用済み | -
Timber | 全て出力 | `e()` をCrashlytics へ転送
アプリID | 末尾に`.debug` がつく | -
アプリ名 | 先頭に`[D]` がつく | -
アプリバージョン | 末尾に`.debug` がつく | -
カスタムApplication クラス | `DebugApplication` | `ReleaseApplication`
署名情報(release.jks) | - | 必須
