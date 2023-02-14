package com.example.huangzhangmusic.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.util.Util
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseVMActivity
import com.example.huangzhangmusic.databinding.ActivityMusicBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.utils.GlideUtils
import com.example.huangzhangmusic.viewModel.MusicViewModel
import com.lzx.starrysky.manager.PlaybackStage
import android.util.Pair as UtilPair

class MusicActivity : BaseVMActivity<ActivityMusicBinding, MusicViewModel>() {
    override fun getLayoutId(): Int = R.layout.activity_music

    override fun getVMClass(): Class<MusicViewModel> = MusicViewModel::class.java

    override fun initView() {
        viewModel.intentState.postValue(IntentState.BOTTOM)

        //设置底部切换
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
                        println("带底部")
                        mBinding.musicBottom.visibility = View.VISIBLE
                        mBinding.musicItem.root.visibility = View.VISIBLE
                        when (musicState.value) {
                            PlaybackStage.PLAYING -> {
                                setPlayingView()
                            }
                            else -> {
                                setPauseView()
                            }
                        }
                    }
                    IntentState.MUSIC -> {//访问 音乐界面
                        mBinding.musicBottom.visibility = View.GONE
                        mBinding.musicItem.root.visibility = View.GONE
                    }
                }
            })
            //播放状态
            musicState.observe(this@MusicActivity, Observer {
                when (it) {
                    PlaybackStage.PLAYING -> {
                        setPlayingView()
                    }
                    PlaybackStage.PAUSE -> {
                        setPauseView()
                    }
                }
            })
            songDetail.observe(this@MusicActivity, Observer {
                mBinding.apply {
                    musicItem.apply {
                        //设置图片
                        GlideUtils.getInstance().setImageSrc(it.songCover, playImg)
                        //歌曲名
                        songName.text = it.songName
                        //歌手
                        singer.text = it.artist
                    }
                }
            })
            progress.observe(this@MusicActivity, Observer {
                mBinding.musicItem.actionProgress.progress = it
            })
        }

    }


    //动画效果
    private val animation by lazy {
        AnimationUtils.loadAnimation(
            this@MusicActivity,
            R.anim.rotate_img
        )
    }

    //设置播放中View
    private fun setPlayingView() {
        println("播放状态")
        mBinding.musicItem.apply {
            //旋转图片
            playImg.startAnimation(animation)
            pause.setImageResource(R.drawable.svg_pause)
        }
    }

    //设置暂停View
    private fun setPauseView() {
        println("暂停状态")
        mBinding.musicItem.apply {
            playImg.clearAnimation()
            pause.setImageResource(R.drawable.svg_play)
        }
    }

    override fun initEvent() {
        viewModel.initPlayer(application)
        mBinding.musicItem.container.setOnClickListener {
            val playImagePair = Pair<View, String>(
                mBinding.musicItem.playImg,
                "playImage1"
            )
            val extras = FragmentNavigatorExtras(playImagePair)
            val bundle = Bundle()

            if (viewModel.songDetail.value?.songCover != null) {
                bundle.putString("imageUrl", viewModel.songDetail.value!!.songCover)
                bundle.putLong("targetId", viewModel.songDetail.value!!.songId.toLong())
                findNavController(R.id.fragment_container_view).navigate(
                    R.id.activity_to_playFragment,
                    bundle,
                    null,
                    extras
                )
            } else {
                //id代表对应的fragmentContainer ==>R.id.fragment_container_view
                //R.id.to_playFragment ==> 配置文件中的 action
                findNavController(R.id.fragment_container_view).navigate(
                    R.id.activity_to_playFragment
                )
            }

            viewModel.intentState.postValue(IntentState.MUSIC)
        }
        mBinding.musicItem.apply {
            pause.setOnClickListener{
                viewModel.pause()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")

    }
}