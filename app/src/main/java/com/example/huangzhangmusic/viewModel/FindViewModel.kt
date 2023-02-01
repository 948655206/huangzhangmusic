package com.example.huangzhangmusic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huangzhangmusic.domain.BannerData
import com.example.huangzhangmusic.repository.Repository
import kotlinx.coroutines.launch

class FindViewModel :ViewModel(){

    val bannerData= MutableLiveData<BannerData>()

    private val mRepository by lazy {
        Repository()
    }

    /**
     * 进入发现页面
     */
    open fun enterFindPage(){
        println("到这里了。。")
        viewModelScope.launch {
            val banner = mRepository.getBanner()
            println("轮播图 ==>$banner")
            bannerData.postValue(banner)
        }
    }
}
