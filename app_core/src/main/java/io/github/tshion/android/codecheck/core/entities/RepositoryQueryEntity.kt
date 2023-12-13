package io.github.tshion.android.codecheck.core.entities

import androidx.annotation.IntRange

/**
 * リポジトリの検索条件
 *
 * @property keyword 検索キーワード
 * @property page ページ番号の指定
 */
public class RepositoryQueryEntity internal constructor(
    public val keyword: String,
    @IntRange(from = 1) public val page: Int,
) {

    /** 並び順 */
    public val order: String = "desc"

    /** 1ページあたりの結果 */
    @IntRange(from = 1, to = 100)
    public val perPage: Int = PER_PAGE

    /** 並び替えのルール */
    public val sort: String? = null


    public companion object {

        /** 1ページ当たりに読み込む件数 */
        public const val PER_PAGE: Int = 50
    }
}
