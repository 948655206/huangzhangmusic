package com.example.huangzhangmusic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huangzhangmusic.domain.BannerData
import com.example.huangzhangmusic.domain.NewAlbum
import com.example.huangzhangmusic.domain.RecommendSong
import com.example.huangzhangmusic.repository.Repository
import kotlinx.coroutines.launch

class FindViewModel :ViewModel(){

    val bannerData= MutableLiveData<BannerData>()
    val recommendSongData=MutableLiveData<RecommendSong>()
    val newestAlbumData=MutableLiveData<NewAlbum>()

    private val mRepository by lazy {
        Repository()
    }

    /**
     * 进入发现页面
     */
    open fun enterFindPage(){
        println("到这里了。。")
        viewModelScope.launch {
            mRepository.apply {
                val banner = getBanner()
//            println("轮播图 ==>$banner")
                bannerData.postValue(banner)

                val recommendSongList = getRecommendSongList()
                println("推荐歌单==>$recommendSongList")
                recommendSongData.postValue(recommendSongList)

                val newestAlbum = getNewestAlbum()
                println("最新歌单==>$newestAlbum")
                newestAlbumData.postValue(newestAlbum)
            }
        }


    }


}
