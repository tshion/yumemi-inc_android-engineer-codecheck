package jp.co.yumemi.android.code_check.models

import android.content.Context
import androidx.annotation.StringRes
import java.lang.ref.WeakReference


typealias ChildrenLoader = () -> List<DemoSpecEntity>
typealias OnTapAction = (WeakReference<DemoViewContract>) -> Unit


/**
 * 操作デモ内容のデータ構造
 *
 * ## メモ
 * * 本クラスを多分木として組み上げることで、操作デモをネスト出来ます
 * * 実装の性質上、オンメモリで完結させる必要があります
 *     * Android OS ではメモリ上のデータは揮発しやすいので、再生成しやすいつくりを目指しています
 *     * ID はインスタンス生成時に決めると再生成する度に変わってしまうので、ビルド時に値が確定するリソースID を用いて固定します
 *
 * @property titleId タイトル文言のリソースID
 * @property subtitle 補足文言 (※省略可)
 * @property childrenLoader 子要素の読み込みタスク (※省略可)
 * @property tapAction タップ時に実行するアクション (※省略可)
 */
class DemoSpecEntity private constructor(
    @StringRes private val titleId: Int,
    val subtitle: String? = null,
    private val childrenLoader: ChildrenLoader? = null,
    val tapAction: OnTapAction? = null,
) {

    /** 子要素 */
    val children: List<DemoSpecEntity>
        get() {
            if (_children == null) {
                _children = childrenLoader?.invoke() ?: emptyList()
            }
            return _children!!
        }
    private var _children: List<DemoSpecEntity>? = null

    /** 操作デモ内容を特定するID */
    val id = titleId


    /** タイトル文言の取得 */
    fun getTitle(context: Context) = context.getString(titleId)

    /**
     * 該当データの検索(幅優先)
     *
     * @param specId 操作デモ内容を特定するID
     */
    fun searchBreadthwise(
        specId: Int,
    ): DemoSpecEntity? {
        val queue = ArrayDeque<DemoSpecEntity>().apply {
            add(this@DemoSpecEntity)
        }
        var result: DemoSpecEntity? = null
        do {
            val candidate = queue.removeFirst()
            if (candidate.id == specId) {
                result = candidate
                break
            } else {
                queue.addAll(candidate.children)
            }
        } while (queue.isNotEmpty())
        return result
    }


    companion object {

        /**
         * 操作デモを行う設定の作成
         *
         * @param titleId タイトル文言のリソースID
         * @param subtitle 補足文言 (※省略可)
         * @param tapAction タップ時に実行するアクション
         */
        fun createDemo(
            @StringRes titleId: Int,
            subtitle: String? = null,
            tapAction: OnTapAction,
        ) = DemoSpecEntity(
            titleId,
            subtitle,
            tapAction = tapAction,
        )

        /**
         * 子要素の操作デモに遷移出来る設定の作成
         *
         * @param titleId タイトル文言のリソースID
         * @param subtitle 補足文言 (※省略可)
         * @param childrenLoader 子要素の読み込みタスク
         */
        fun createNavigator(
            @StringRes titleId: Int,
            subtitle: String? = null,
            childrenLoader: ChildrenLoader,
        ) = DemoSpecEntity(
            titleId,
            subtitle,
            childrenLoader = childrenLoader,
        )
    }
}
