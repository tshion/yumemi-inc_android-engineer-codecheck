package io.github.tshion.android.codecheck.core.entities

import androidx.annotation.IntRange

/**
 * リポジトリの検索条件
 *
 * @property keyword 検索キーワード
 * @property page ページ番号の指定
 */
public class RepositoryQueryEntity private constructor(
    public val keyword: String,
    @IntRange(from = 1) public val page: Int,
) {

    /** 並び順 */
    public val order: String = "desc"

    /** 1ページあたりの結果 */
    @IntRange(from = 1, to = 100)
    public val perPage: Int = 50

    /** 並び替えのルール */
    public val sort: String? = null


    public companion object {

        /**
         * インスタンス生成
         *
         * @throws IllegalArgumentException 空文字 or null or 300文字を超える場合
         * @throws IndexOutOfBoundsException 意図しないページ数を指定された場合
         */
        public fun newInstance(
            keyword: String?,
            page: Int,
        ): RepositoryQueryEntity {
            if (keyword.isNullOrBlank() || 300 < keyword.length) {
                throw IllegalArgumentException()
            }
            if (page < 1) {
                throw IndexOutOfBoundsException()
            }
            return RepositoryQueryEntity(keyword, page)
        }
    }
}
