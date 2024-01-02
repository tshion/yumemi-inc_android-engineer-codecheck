package jp.co.yumemi.android.code_check.pages.detail

import android.content.res.Resources
import android.os.Parcelable
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import jp.co.yumemi.android.code_check.R
import kotlinx.parcelize.Parcelize

/**
 * 詳細画面の表示データ
 *
 * @property forkCount フォーク数
 * @property imageText 表示画像の説明文言
 * @property imageUrl 表示画像のURL
 * @property issueCount 未解決のIssue 数
 * @property language プログラミング言語
 * @property starCount スター数
 * @property title 表示タイトル
 * @property watcherCount ウォッチ数
 */
@Parcelize
data class DetailViewData(
    val forkCount: String,
    val imageText: String?,
    val imageUrl: String?,
    val issueCount: String,
    val language: String,
    val starCount: String,
    val title: String,
    val watcherCount: String,
) : Parcelable {

    companion object {

        fun parse(
            data: RepositoryEntity,
            resources: Resources,
        ): DetailViewData {
            val url = data.ownerIconUrl?.toString()
            return DetailViewData(
                forkCount = resources.getString(
                    R.string.page_detail_label_forks,
                    data.forkCount,
                ),
                imageText = if (url != null) data.ownerName else null,
                imageUrl = url,
                issueCount = resources.getString(
                    R.string.page_detail_label_issues,
                    data.issueCount,
                ),
                language = resources.getString(
                    R.string.page_detail_label_language,
                    data.language,
                ),
                starCount = resources.getString(
                    R.string.page_detail_label_stars,
                    data.starCount,
                ),
                title = "${data.ownerName}/${data.name}",
                watcherCount = resources.getString(
                    R.string.page_detail_label_watchers,
                    data.watcherCount,
                ),
            )
        }
    }
}
