package jp.co.yumemi.android.code_check.pages.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private val args: DetailFragmentArgs by navArgs()

    private var binding: PageDetailBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.data

        binding = PageDetailBinding.bind(view).apply {
            pageDetailHeader.setupWith(findNavController())

            pageDetailImage.load(data.imageUrl)
            pageDetailImage.contentDescription = data.imageText

            pageDetailLabelTitle.text = data.title

            pageDetailLabelForks.text = data.forkCount
            pageDetailLabelIssues.text = data.issueCount
            pageDetailLabelLanguage.text = data.language
            pageDetailLabelStars.text = data.starCount
            pageDetailLabelWatchers.text = data.watcherCount
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
