package com.example.huangzhangmusic.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseVMActivity
import com.example.huangzhangmusic.databinding.ActivityMusicBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.utils.GlideUtils
import com.example.huangzhangmusic.viewModel.MusicViewModel
import com.lzx.starrysky.manager.PlaybackStage

class MusicActivity : BaseVMActivity<ActivityMusicBinding, MusicViewModel>() {
    override fun getLayoutId(): Int = R.layout.activity_music

    override fun getVMClass(): Class<MusicViewModel> = MusicViewModel::class.java

    override fun initView() {
        viewModel.intentState.postValue(IntentState.BOTTOM)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        mBinding.musicBottom.setupWithNavController(
            navHostFragment.navController
        )
    }

    override fun initObserver() {
        viewModel.apply {
            //浏览状态
            intentState.observe(this@MusicActivity, Observer {
                when (it) {
                    IntentState.BOTTOM -> {
                        mBinding.musicBottom.visibility = View.VISIBLE
                        mBinding.musicItem.root.visibility=View.VISIBLE
                    }
                    IntentState.MUSIC -> {//访问 音乐界面
                        mBinding.musicBottom.visibility = View.GONE
                        mBinding.musicItem.root.visibility=View.GONE
                    }
                }
            })
            musicState.observe(this@MusicActivity, Observer {
                when (it) {
                    PlaybackStage.PLAYING -> {

                    }
                    PlaybackStage.PAUSE -> {

                    }
                }
            })
            songDetail.observe(this@MusicActivity, Observer {
                mBinding.apply {
                    musicItem.apply {
                        //设置图片
                        GlideUtils.getInstance().setImageSrc(it.songCover,playImg)
                        //歌曲名
                        songName.text=it.songName
                        //歌手
                        singer.text=it.artist
                    }
                }
            })
            progress.observe(this@MusicActivity, Observer {
                mBinding.musicItem.actionProgress.progress = it
            })
        }

    }

    override fun initEvent() {
        viewModel.initPlayer(application)
        mBinding.musicItem.container.setOnClickListener {
            val playImagePair = Pair<View, String>(mBinding.musicItem.playImg,
                "playImage1")
            val extras = FragmentNavigatorExtras(playImagePair)
            val bundle = Bundle()

            if (viewModel.songDetail.value?.songCover!=null) {
                bundle.putString("imageUrl",viewModel.songDetail.value!!.songCover)
                bundle.putLong("targetId", viewModel.songDetail.value!!.songId.toLong())
            }
            //id代表对应的fragmentContainer ==>R.id.fragment_container_view
            //R.id.to_playFragment ==> 配置文件中的 action
            findNavController(R.id.fragment_container_view).navigate(
                R.id.activity_to_playFragment,
                bundle,
                null,
                extras
            )
            viewModel.intentState.postValue(IntentState.MUSIC)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")

    }
}