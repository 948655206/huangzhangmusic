package com.example.huangzhangmusic.fragment.bottom

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.MusicActivity
import com.example.huangzhangmusic.adapter.AlbumAdapter
import com.example.huangzhangmusic.adapter.ChartAdapter
import com.example.huangzhangmusic.adapter.RecommendAdapter
import com.example.huangzhangmusic.base.BaseVMFragment
import com.example.huangzhangmusic.databinding.FragmentFindBinding
import com.example.huangzhangmusic.domain.Const
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.viewModel.FindViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.AlphaPageTransformer


/**
 * 发现页
 */
class FindFragment : BaseVMFragment<FragmentFindBinding, FindViewModel>() {

    private val musicViewModel by lazy {
        (requireActivity() as MusicActivity).viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_find

    private val chartAdapter by lazy {
        ChartAdapter(null)
    }

    //推荐歌单适配器
    private val recommendAdapter by lazy {
        RecommendAdapter()
    }

    //最新专辑适配器
    private val newAlbumAdapter by lazy {
        AlbumAdapter()
    }

    override fun setView() {
        mBinding.viewpager.apply {
            setAdapter(chartAdapter)
            isAutoLoop(true)
            addPageTransformer(AlphaPageTransformer())
            indicator = CircleIndicator(context)
            setBannerGalleryMZ(20)
        }

        mBinding.apply {
            recommendRv.apply {
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapter=recommendAdapter
            }
            albumRv.apply {
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapter=newAlbumAdapter
            }
        }


        //监听
        mBinding.refreshLayout
            .setReboundDuration(1000)
            .setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    println("刷新中...")
                    setEvent()
                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    println("加载更多...")
                }
            })
    }

    override fun setObserver() {
        viewModel.apply {
            bannerData.observe(this@FindFragment, Observer {
                val list = it.banners
                chartAdapter.setData(list)
                chartAdapter.setOnBannerListener { data, position ->
                    when (data.targetType) {
                        Const.TYPE_SONG -> {
                            //单曲
                            val bundle = Bundle()
                            bundle.putLong("targetId", data.targetId)
                            musicViewModel.intentState.postValue(IntentState.MUSIC)
                            findNavController().navigate(R.id.activity_to_playFragment, bundle)
                        }
                        Const.TYPE_ALUM -> {
                            //专辑
                        }
                    }

                }

            })
            recommendSongData.observe(this@FindFragment, Observer {
                println("观察者收到信息")
                mBinding.refreshLayout.finishRefresh()
                recommendAdapter.submitList(it.result)
            })
            newestAlbumData.observe(this@FindFragment, Observer {
                newAlbumAdapter.submitList(it.albums)
            })
        }
    }

    override fun setEvent() {
        //发送页面信息
        viewModel.enterFindPage()
    }

    override fun getVMClass(): Class<FindViewModel> {
        return FindViewModel::class.java
    }


}

