package com.example.huangzhangmusic.fragment


import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.MusicActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.databinding.FragmentPlayBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.utils.GlideUtils
import com.example.huangzhangmusic.utils.TimeUtils
import com.lzx.starrysky.manager.PlaybackStage

class PlayFragment : BaseFragment<FragmentPlayBinding>() {

    private val musicViewModel by lazy {
        (requireActivity() as MusicActivity).viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_play
    }

    override fun initView() {
        println("初始化...")
        //单曲Id
        val targetId = arguments?.getLong("targetId")
        if (targetId != null) {
            musicViewModel.apply {
                println("targetId==>$targetId")
                playMusic(targetId)
            }
        } else {
            println("targetId为空..")
        }
        //图片url
        val imageUrl=arguments?.getString("imageUrl")
        if (imageUrl != null) {
            GlideUtils.getInstance().setImageSrc(imageUrl,mBinding.playImg)
        }else{
            println("imageUrl为空..")
        }
    }


    override fun initEvent() {
        //点击监听
        mBinding.apply {
            downBtn.setOnClickListener {
                //返回
                findNavController().navigateUp()
                musicViewModel.intentState.postValue(IntentState.BOTTOM)
            }
            pause.setOnClickListener {
                //暂停
                musicViewModel.pause()
            }
            next.setOnClickListener {
                musicViewModel.nextMusic()
            }
            up.setOnClickListener {
                musicViewModel.upMusic()
            }
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null) {
                        musicViewModel.player.seekTo(seekBar.progress.toLong(), false)
                        musicViewModel.pause()
                    }
                }

            })
        }
    }

    override fun initObserver() {
        //歌曲详情
        musicViewModel.songDetail.observe(this, Observer {
            println("歌曲详情变化了..")
            mBinding.apply {
                it.apply {
                    GlideUtils.getInstance().setImageSrcByRectangle(it.songCover, playImg)
                    mBinding.songName.text = songName
                    singer.text = artist
                    println("duration==>$duration")
                    endTime.text = TimeUtils.timeFormatMMSS(duration)

                    //设置进度条最大值
                    seekbar.max = duration.toInt()
                }
            }
        })
        musicViewModel.apply {
            player.playbackState().observe(this@PlayFragment, Observer {
                when (it.stage) {
                    PlaybackStage.PLAYING -> {
                        mBinding.pause.setImageResource(R.drawable.svg_pause)
                        println("歌曲列表大小==>${player.getPlayList().size}")
                        for (i in 0 until player.getPlayList().size){
                            player.getPlayList().get(i).apply {
                                println("歌曲$i==>$songName")
                            }
                        }
                    }
                    PlaybackStage.PAUSE -> {
                        mBinding.pause.setImageResource(R.drawable.svg_play)
                    }
                }
            })

            currentTime.observe(this@PlayFragment, Observer {
                mBinding.statTime.text = TimeUtils.timeFormatMMSS(currentTime.value!!)
                mBinding.seekbar.progress = currentTime.value!!.toInt()
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy123")
    }

}