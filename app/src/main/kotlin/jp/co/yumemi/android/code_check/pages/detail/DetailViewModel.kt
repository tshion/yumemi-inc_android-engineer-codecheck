package jp.co.yumemi.android.code_check.pages.detail

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity

/**
 * 詳細画面のViewModel
 */
class DetailViewModel : ViewModel() {

    /**
     * 表示データ
     */
    var data: DetailViewData? = null
        private set


    fun setup(
        data: RepositoryEntity,
        resources: Resources,
    ) {
        this.data = DetailViewData(data, resources)
    }
}
