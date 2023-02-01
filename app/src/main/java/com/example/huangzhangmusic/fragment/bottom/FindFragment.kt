package com.example.huangzhangmusic.fragment.bottom

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.adapter.ChartAdapter
import com.example.huangzhangmusic.base.BaseVMFragment
import com.example.huangzhangmusic.databinding.FragmentFindBinding
import com.example.huangzhangmusic.domain.Banner
import com.example.huangzhangmusic.domain.Const
import com.example.huangzhangmusic.viewModel.FindViewModel
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.transformer.AlphaPageTransformer


/**
 * 发现页
 */
class FindFragment :BaseVMFragment<FragmentFindBinding, FindViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_find

    private val chartAdapter by lazy {
        ChartAdapter(null)
    }

    override fun setView() {
        mBinding.viewpager.apply {
            setAdapter(chartAdapter)
            isAutoLoop(true)
            addPageTransformer(AlphaPageTransformer())
            indicator = CircleIndicator(context)
            setBannerGalleryMZ(20)
        }
    }

    override fun initObserver() {
        viewModel.apply {
            bannerData.observe(this@FindFragment, Observer {
                val list = it.banners
                chartAdapter.setData(list)
                chartAdapter.setOnBannerListener { data, position ->
                    when (data.targetType) {
                        Const.TYPE_SONG -> {
                            //单曲
                        }
                        Const.TYPE_ALUM -> {
                            //专辑
                        }
                    }

                }
            })
        }
    }

    override fun setEvent() {
        viewModel.enterFindPage()
    }

    override fun getVMClass(): Class<FindViewModel>{
        return FindViewModel::class.java
    }


}

