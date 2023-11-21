package jp.co.yumemi.android.code_check.organisms.repository_list_view

import io.github.tshion.android.codecheck.core.entities.RepositoryEntity

/**
 * リポジトリ一覧項目の表示データ
 */
data class RepositoryListItemViewData(
    val original: RepositoryEntity,
) {

    val text = "${original.ownerName}/${original.name}"
}
