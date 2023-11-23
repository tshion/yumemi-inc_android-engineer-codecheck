package jp.co.yumemi.android.code_check.pages.detail

import android.content.res.Resources
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import jp.co.yumemi.android.code_check.R

/**
 * 詳細画面の表示データ
 */
class DetailViewData(
    data: RepositoryEntity,
    resources: Resources,
) {

    /** フォーク数 */
    val forkCount = resources.getString(
        R.string.page_detail_label_forks,
        data.forkCount,
    )

    /** 表示画像の説明文言 */
    val imageText: String?

    /** 表示画像のURL */
    val imageUrl: String?

    /** 未解決のIssue 数 */
    val issueCount = resources.getString(
        R.string.page_detail_label_issues,
        data.issueCount,
    )

    /** プログラミング言語 */
    val language = resources.getString(
        R.string.page_detail_label_language,
        data.language,
    )

    /** スター数 */
    val starCount = resources.getString(
        R.string.page_detail_label_stars,
        data.starCount,
    )

    /** 表示タイトル */
    val title = "${data.ownerName}/${data.name}"

    /** ウォッチ数 */
    val watcherCount = resources.getString(
        R.string.page_detail_label_watchers,
        data.watcherCount,
    )


    init {
        val url = data.ownerIconUrl?.toString()
        imageText = if (url != null) data.ownerName else null
        imageUrl = url
    }
}
