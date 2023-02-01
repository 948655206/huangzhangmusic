package com.example.huangzhangmusic.fragment

import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LiveData
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.databinding.FragmentWebviewBinding

class WebViewFragment :BaseFragment<FragmentWebviewBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_webview
    }

    override fun initEvent() {
        val url = arguments?.getString("url")
        println("url ==>$url")
        if (url != null) {
            mBinding.webView.loadUrl("https://y.music.163.com/m/at/63b02c423f08e068adabd182")
        }
    }
}