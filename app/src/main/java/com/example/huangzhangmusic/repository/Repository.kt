package com.example.huangzhangmusic.repository

import com.example.huangzhangmusic.api.RetrofitClient
import com.example.huangzhangmusic.api.RetrofitClient.apiService

class Repository {

    private val mapString = mutableMapOf<String, String>()

    suspend fun getLoginByEmail(phone: String, password: String) =
        apiService.getLoginByEmail(phone, password)

    suspend fun getBanner() = apiService.getBanner()

    /**
     * 获取歌曲url
     */
    suspend fun getSongUrl(id: Long?) = apiService.getSongUrl(id, "lossless")

    /**
     * 获取歌曲详情
     */
    suspend fun getSongDetail(id: Long?) = apiService.getSongDetail(id)

    /**
     * 获取推荐歌单
     */
    suspend fun getRecommendSongList() = apiService.getRecommendSongList()

    /**
     * 获取最新专辑
     */
    suspend fun getNewestAlbum()= apiService.getNewestAlbum()
}

