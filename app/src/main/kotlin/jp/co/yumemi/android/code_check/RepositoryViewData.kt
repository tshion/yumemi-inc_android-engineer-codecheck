package jp.co.yumemi.android.code_check

import androidx.annotation.IntRange

/**
 * リポジトリの表示データ
 *
 * @property forkCount フォーク数
 * @property imageUrl 表示画像のURL
 * @property issueCount 未解決のIssue 数
 * @property language プログラミング言語
 * @property starCount スター数
 * @property title 表示タイトル
 * @property watcherCount ウォッチ数
 */
data class RepositoryViewData(
    @IntRange(from = 0) val forkCount: Int,
    val imageUrl: String?,
    @IntRange(from = 0) val issueCount: Int,
    val language: String?,
    @IntRange(from = 0) val starCount: Int,
    val title: String,
    @IntRange(from = 0) val watcherCount: Int,
)
