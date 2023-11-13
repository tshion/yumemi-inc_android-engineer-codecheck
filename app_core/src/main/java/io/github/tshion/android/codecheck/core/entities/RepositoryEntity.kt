package io.github.tshion.android.codecheck.core.entities

import androidx.annotation.IntRange
import java.net.URL

/**
 * リポジトリ情報
 *
 * @property forkCount フォーク数
 * @property issueCount 未解決のIssue 数
 * @property language プログラミング言語
 * @property name リポジトリ名
 * @property ownerIconUrl 所有者のアイコンURL
 * @property ownerName 所有者の名前
 * @property starCount スター数
 * @property watcherCount ウォッチ数
 */
public class RepositoryEntity private constructor(
    @IntRange(from = 0) public val forkCount: Int,
    @IntRange(from = 0) public val issueCount: Int,
    public val language: String?,
    public val name: String,
    public val ownerIconUrl: URL?,
    public val ownerName: String,
    @IntRange(from = 0) public val starCount: Int,
    @IntRange(from = 0) public val watcherCount: Int,
) {

    public companion object {

        /**
         * インスタンスへ変換 or null
         */
        public fun parseOrNull(
            forkCount: Int,
            issueCount: Int,
            language: String?,
            name: String,
            ownerIconUrl: String?,
            ownerName: String?,
            starCount: Int,
            watcherCount: Int,
        ): RepositoryEntity? {
            if (forkCount < 0) return null
            if (issueCount < 0) return null
            if (starCount < 0) return null
            if (watcherCount < 0) return null
            ownerName ?: return null

            val result = ownerIconUrl?.let {
                runCatching { URL(ownerIconUrl) }
            }
            if (result != null && result.getOrNull() == null) {
                return null
            }

            return RepositoryEntity(
                forkCount,
                issueCount,
                language,
                name,
                result?.getOrNull(),
                ownerName,
                starCount,
                watcherCount,
            )
        }
    }
}
