package jp.co.yumemi.android.code_check

import androidx.test.platform.app.InstrumentationRegistry


/**
 * リソースID の文字列表記へ変換
 *
 * ※参考文献: [Androidテスト全書](https://peaks.cc/books/android_testing) p. 193
 */
fun Int.toName(): String = InstrumentationRegistry.getInstrumentation()
    .targetContext
    .resources
    .getResourceName(this)
