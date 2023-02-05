package com.example.huangzhangmusic.viewModel

import android.app.Application
import android.os.Message
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.utils.TimeUtils
import com.lzx.starrysky.OnPlayProgressListener
import com.lzx.starrysky.StarrySky
import com.lzx.starrysky.StarrySkyInstall
import com.lzx.starrysky.manager.PlaybackStage
import com.lzx.starrysky.notification.INotification

/**
 * 音乐ViewModel
 * 监听播放状态 和 当前页面
 */
class MusicViewModel : ViewModel() {

     val player by lazy {
        StarrySky.with()
    }

    //当前访问页面
    val intentState = MutableLiveData<IntentState>()

    val currentTime = MutableLiveData<Long>()

    val progress = MutableLiveData<Int>()

    val musicState=MutableLiveData<String>()

    //设置播放器
    fun initPlayer(application: Application) {
        println("初始化播放器")
        StarrySkyInstall
            .init(application)
            .connService(true)
            .setNotificationSwitch(true)
            .setNotificationType(INotification.SYSTEM_NOTIFICATION)
            .apply()

    }

    fun playMusic(url: String) {
        println("开始播放")
        musicState.postValue(PlaybackStage.PLAYING)
        player.playMusicByUrl(url)
        player.setOnPlayProgressListener(object :OnPlayProgressListener{
            override fun onPlayProgress(currPos: Long, duration: Long) {
                currentTime.postValue(currPos)
                progress.postValue(((currPos.toFloat()/duration)*100).toInt())
            }
        },"progress")
    }

    fun nextMusic() {
        musicState.postValue(PlaybackStage.PLAYING)
        player.skipToPrevious()
    }

    fun upMusic() {
        musicState.postValue(PlaybackStage.PLAYING)
        player.skipToNext()
    }

    //暂停 或者 继续播放
    fun pause() {
        if (player.isPlaying()) {
            musicState.postValue(PlaybackStage.PAUSE)
            player.pauseMusic()
        } else {
            //继续播放
            musicState.postValue(PlaybackStage.PLAYING)
            player.restoreMusic()
        }
    }



}



