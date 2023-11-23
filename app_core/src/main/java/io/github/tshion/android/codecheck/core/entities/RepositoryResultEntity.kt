package io.github.tshion.android.codecheck.core.entities

/**
 * リポジトリの検索結果
 *
 * @property hasMore 追加取得できる検索結果が存在しているかどうか
 * @property items リポジトリ情報の一覧
 */
public data class RepositoryResultEntity(
    val hasMore: Boolean,
    val items: List<RepositoryEntity>,
)