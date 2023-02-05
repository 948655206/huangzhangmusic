package com.example.huangzhangmusic.fragment


import android.content.res.Configuration
import android.view.Window
import android.view.WindowManager
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.MusicActivity
import com.example.huangzhangmusic.base.BaseActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.base.BaseVMFragment
import com.example.huangzhangmusic.databinding.FragmentPlayBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.domain.SongDetail
import com.example.huangzhangmusic.utils.GlideUtils
import com.example.huangzhangmusic.utils.TimeUtils
import com.example.huangzhangmusic.viewModel.PlayViewModel
import com.lzx.starrysky.manager.PlaybackStage
import okhttp3.internal.wait
import java.util.*

class PlayFragment : BaseVMFragment<FragmentPlayBinding, PlayViewModel>() {

    private val musicViewModel by lazy {
        (requireActivity() as MusicActivity).viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_play
    }

    override fun setView() {
        println("初始化...")
        //单曲Id
        val targetId = arguments?.getLong("targetId")
        if (targetId != null) {
            viewModel.apply {
                openSingUrl(targetId)
                getSongDetail(targetId)
            }
        } else {
            println("targetId为空..")
        }

    }


    override fun setEvent() {
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
            seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
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
                        musicViewModel.player.seekTo(seekBar.progress.toLong(),false)
                        musicViewModel.pause()
                    }
                }

            })
        }
    }

    override fun initObserver() {
        viewModel.songData.observe(this, Observer {
            for (songData in it.data) {
                musicViewModel.playMusic(songData.url)
            }
        })
        viewModel.songDetail.observe(this, Observer {
            mBinding.apply {
                for (song in it.songs) {
                    GlideUtils.getInstance().setImageSrc(song.al.picUrl, playImg)
                    songName.text = song.al.name
                    singer.text = song.ar.get(0).name
                    endTime.text=TimeUtils.timeFormatMMSS(song.dt)
                    //设置进度条最大值
                    seekbar.max=song.dt.toInt()
                }
            }
        })
        musicViewModel.apply {
            player.playbackState().observe(this@PlayFragment, Observer {
                when (it.stage) {
                    PlaybackStage.PLAYING -> {
                        mBinding.pause.setImageResource(R.drawable.svg_pause)
                    }
                    PlaybackStage.PAUSE -> {
                        mBinding.pause.setImageResource(R.drawable.svg_play)
                    }
                }
            })

            currentTime.observe(this@PlayFragment, Observer {
                mBinding.statTime.text=TimeUtils.timeFormatMMSS(currentTime.value!!)
                mBinding.seekbar.progress= currentTime.value!!.toInt()
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy123")
    }

    override fun getVMClass(): Class<PlayViewModel> {
        return PlayViewModel::class.java
    }
}