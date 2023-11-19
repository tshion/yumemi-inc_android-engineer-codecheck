package jp.co.yumemi.android.code_check.pages.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.PageDetailBinding

/**
 * 詳細画面
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 * * [DetailViewModel] に表示データを設定している
 */
class DetailFragment : Fragment(R.layout.page_detail) {

    private var binding: PageDetailBinding? = null

    private val viewModel by navGraphViewModels<DetailViewModel>(R.id.nav_graph_entry_point)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = viewModel.data ?: throw IllegalArgumentException("表示データを設定してください")

        binding = PageDetailBinding.bind(view).apply {
            pageDetailHeader.setTitle(R.string.page_detail_title)

            pageDetailImage.load(data.imageUrl)
            pageDetailImage.contentDescription = data.imageText

            pageDetailLabelTitle.text = data.title

            pageDetailLabelForks.text = getString(
                R.string.page_detail_label_forks,
                data.forkCount,
            )
            pageDetailLabelIssues.text = getString(
                R.string.page_detail_label_issues,
                data.issueCount,
            )
            pageDetailLabelLanguage.text = getString(
                R.string.page_detail_label_language,
                data.language,
            )
            pageDetailLabelStars.text = getString(
                R.string.page_detail_label_stars,
                data.starCount,
            )
            pageDetailLabelWatchers.text = getString(
                R.string.page_detail_label_watchers,
                data.watcherCount,
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
