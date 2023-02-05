package com.example.huangzhangmusic.activity

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseVMActivity
import com.example.huangzhangmusic.databinding.ActivityMusicBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.viewModel.MusicViewModel
import com.lzx.starrysky.manager.PlaybackStage
import com.youth.banner.util.LogUtils

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
                    }
                    IntentState.MUSIC -> {
                        mBinding.musicBottom.visibility = View.GONE
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
            progress.observe(this@MusicActivity, Observer {
                println("progress==>$it")
            })
        }

    }

    override fun initEvent() {
        viewModel.initPlayer(application)

    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")

    }
}