package io.github.tshion.android.codecheck.core.repositories

import io.github.tshion.android.codecheck.core.entities.RepositoryQueryEntity
import io.github.tshion.android.codecheck.core.entities.RepositoryResultEntity

/**
 * GitHub 関連データのリポジトリ定義
 */
public interface GitHubRepositoryContract {

    /**
     * 検索の実行
     */
    public suspend fun search(
        query: RepositoryQueryEntity,
    ): RepositoryResultEntity
}