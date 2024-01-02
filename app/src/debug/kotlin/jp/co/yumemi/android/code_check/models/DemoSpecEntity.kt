package jp.co.yumemi.android.code_check.models

import java.lang.ref.WeakReference
import java.util.UUID


typealias ChildrenLoader = () -> List<DemoSpecEntity>
typealias OnTapAction = (WeakReference<DemoViewContract>) -> Unit


/**
 * 操作デモ内容のデータ構造
 *
 * @property title タイトル文言
 * @property subtitle 補足文言 (※任意)
 * @property childrenLoader 子要素の読み込みタスク (※任意)
 * @property tapAction タップ時に実行するアクション (※任意)
 */
class DemoSpecEntity private constructor(
    val title: String,
    val subtitle: String? = null,
//    val childrenLoader: ChildrenLoader? = null,
    val tapAction: OnTapAction? = null,
) {

    /** 操作デモ内容を特定するID */
    val dataId = UUID.randomUUID().toString()


    companion object {

        /**
         * 操作デモを行う設定の作成
         *
         * @param title タイトル文言
         * @param subtitle 補足文言
         * @param tapAction タップ時に実行するアクション
         */
        fun createDemo(
            title: String,
            subtitle: String? = null,
            tapAction: OnTapAction,
        ) = DemoSpecEntity(
            title,
            subtitle,
            tapAction = tapAction,
        )

        /**
         * 子要素の操作デモに遷移出来る設定の作成
         *
         * @param title タイトル文言
         * @param subtitle 補足文言
         * @param childrenLoader 子要素の読み込みタスク
         */
//        fun createNavigator(
//            title: String,
//            subtitle: String? = null,
//            childrenLoader: ChildrenLoader,
//        ) = DemoSpecEntity(
//            title,
//            subtitle,
//            childrenLoader = childrenLoader,
//        )
    }
}
