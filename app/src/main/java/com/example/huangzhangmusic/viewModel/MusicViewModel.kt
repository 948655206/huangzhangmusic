package com.example.huangzhangmusic.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.domain.SongDetail
import com.example.huangzhangmusic.domain.SongUrlData
import com.example.huangzhangmusic.repository.Repository
import com.lzx.starrysky.OnPlayProgressListener
import com.lzx.starrysky.SongInfo
import com.lzx.starrysky.StarrySky
import com.lzx.starrysky.StarrySkyInstall
import com.lzx.starrysky.manager.PlaybackStage
import com.lzx.starrysky.notification.INotification
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import java.net.HttpURLConnection

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

    val musicState = MutableLiveData<String>()

    val songDetail = MutableLiveData<SongInfo>()


    private val mRepository by lazy {
        Repository()
    }

    /**
     * 播放当前选中歌曲 并且添加到播放列表中
     */
    open fun playMusic(targetId: Long?) {
        viewModelScope.launch {
            val songInfo = SongInfo.create("0")
            //获取歌曲URL
            mRepository.apply {
                getSongUrl(targetId).apply {
                    if (code == HttpURLConnection.HTTP_OK) {
                        songInfo.songUrl = data.get(0).url
                        if (data.get(0).fee==1) {
                            val time = data.get(0).freeTrialInfo.end - data.get(0).freeTrialInfo.start
                            songInfo.duration= time.toLong()*1000
                        }
                    } else {
                        //可以发送歌曲URL
                    }
                }
                getSongDetail(targetId).apply {
                    if (code == HttpURLConnection.HTTP_OK) {
                        songs.get(0).apply {
                            songInfo.apply {
                                songName = name
                                songId = id.toString()
                                artist = ar.get(0).name
                                songCover = al.picUrl
                                //歌曲长度
                                if (fee!=1) {
                                    duration = dt
                                }
                            }
                        }
                    }
                }
                //发送歌曲详情
                songDetail.postValue(songInfo)
                //设置为播放状态
                musicState.postValue(PlaybackStage.PLAYING)
                //添加到播放器并且播放
                player.apply {
                    addSongInfo(songInfo)
                    playMusicByInfo(songInfo)
                    setOnPlayProgressListener(object : OnPlayProgressListener {
                        override fun onPlayProgress(currPos: Long, duration: Long) {
                            currentTime.postValue(currPos)
                            progress.postValue(((currPos.toFloat() / duration) * 100).toInt())
                        }
                    }, "progress")
                }
                songInfo.apply {
                    println("歌曲图片url==>$songCover")
                }

            }
        }
    }

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

    fun nextMusic() {
        musicState.postValue(PlaybackStage.PLAYING)
        player.skipToPrevious()
        songDetail.postValue(player.getNowPlayingSongInfo())
    }

    fun upMusic() {
        musicState.postValue(PlaybackStage.PLAYING)
        player.skipToNext()
        songDetail.postValue(player.getNowPlayingSongInfo())
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



