package jp.co.yumemi.android.code_check

import androidx.fragment.app.Fragment

/**
 * 検索フローの取得
 */
fun Fragment.searchUseCase() = (requireActivity().application as MainApplication).searchUseCase
