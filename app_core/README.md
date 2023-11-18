# app_core (Kotlin モジュール)
アプリで取り扱うデータや、そのロジックを管理するモジュール。
大まかなクラス関係を説明すると、下記になります。

![Classes](https://www.plantuml.com/plantuml/svg/dLLTJnD157sVNp7f4mRTyEBB1YbAZ3O1n9B6vs5xssxOpk3CR0WX9C16yEC1V50ZWf46JT18OgA619GVCzqs_0k7_MYtIxSOzg7fthdtpBbdpjmT5v9mwTOTX2oRMmxWNAc0frZPTI27YS2bGdVb2SpTdUbP3ICwmo52u5aWtAgL14mG0NWPOVtSgjgoviuLOO49MpA-PE87jdoOZ4mmAZcnP90_B0AGpgeDOcWUbXOPBvjuLdARLlCtyGAfWeaBLDumSHUe2C9rv7qgRRc4LX1b4h035ObP9SNDnAs3tVRFXlBUAE-xyauHWg0iWHRYH6qCLnYVds0kbS6sOSmMmeLqq26qwWRq8hPXc99w9nH6s285Nh0OBN77nANYP3uHdxwKVd5GwRqMYRHga0o7yMMTB6rhHUl41JpYqwxZ31c6eTNIl_bO6svNQu4uwQPDizDk03ZIFFdSF7wfDIhZYyehoxlANAy-gKs0K2eMiF-duPzk_Jz8Hx7hSKYAEm1XZad9wlqWjCkKzrLvUshzM7dFzV-Mzyp_zAEFrnwxa3PSolHXiEFxuDKmSEjTmp_QyLSFz4ja7ru67bVXEpXN1c7bedBWCp62Nhez4bcBSTcnguvJRPS17acIlTIYMZiAhrPhRxzvyhjFhybR6ZTR8sAASKti7cCE41hTBmbrOU99MyZHjBXZCS5--lTiaKsn_MkxzN4N8PjAu1LYGUPeYdc6msdewOK2Pdz9hfn8NSAPkyKp4gkrR-Tl6shjlLf_fRnDFQZK-fdcd-f8Mw3zzn3fHmzlewnlZp6MCV7YO-bT7JM2SpO6KV8tlxItDoBDClDYfB7esw0BprTtccVxgHqDTq7dP9dAFtZHslg0-Y_73GxWghpNoZjKtdweMBhT627JCJszPcInMBEqDH4QroD2VrF_0G00 "Classes")



## 開発メモ
* Kotlin Explicit API モードがstrict レベルで有効となっています
* インスタンス生成時に検証を行うEntity は、普通の`class` 実装となっています
    * `data class` の場合、`copy()` した際に検証をかけられないため



## 関連リンク
### 依存ライブラリ ([build.gradle](./build.gradle))
* コア
    * AndroidX
        * [annotation](https://developer.android.com/jetpack/androidx/releases/annotation) ([Maven Google](https://mvnrepository.com/artifact/androidx.annotation/annotation))
* テスト
    * [JUnit4](https://github.com/junit-team/junit4) ([Maven Central](https://mvnrepository.com/artifact/junit/junit))
    * Kotlin Coroutines BOM ([Maven Central](https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-bom))
        * [Kotlin Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/README.md)
    * [MockK](https://github.com/mockk/mockk) ([Maven Central](https://mvnrepository.com/artifact/io.mockk/mockk))
