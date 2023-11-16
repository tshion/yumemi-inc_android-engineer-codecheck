package jp.co.yumemi.android.code_check

import android.content.res.Resources


/**
 * dp からpx へ変換
 *
 * * Jetpack Compose では同様機能が提供されているので、本メソッドを使わないでください
 * * [参考文献](https://developer.android.com/training/multiscreen/screendensities#dips-pels)
 */
fun Float.toPx(resources: Resources): Int {
    val scale: Float = resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}
