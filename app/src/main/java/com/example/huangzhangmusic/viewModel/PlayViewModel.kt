package com.example.huangzhangmusic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huangzhangmusic.domain.SongDetail
import com.example.huangzhangmusic.domain.SongUrlData
import com.example.huangzhangmusic.repository.Repository
import kotlinx.coroutines.launch

class PlayViewModel : ViewModel() {

    val songData = MutableLiveData<SongUrlData>()

    val songDetail=MutableLiveData<SongDetail>()


    private val mRepository by lazy {
        Repository()
    }

    /**
     * 打开单曲
     */
    open fun openSingUrl(targetId: Long) {
        viewModelScope.launch {
            songData.postValue(mRepository.getSongUrl(targetId))
        }
    }

    /**
     * 获取单曲详情
     */
    open fun getSongDetail(targetId: Long){
        viewModelScope.launch {
            songDetail.postValue(mRepository.getSongDetail(targetId))
            println("歌曲详情==>${mRepository.getSongDetail(targetId)}")
        }
    }
}
