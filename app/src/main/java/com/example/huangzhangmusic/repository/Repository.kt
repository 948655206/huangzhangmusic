package com.example.huangzhangmusic.repository

import com.example.huangzhangmusic.api.RetrofitClient

class Repository {

    private val mapString= mutableMapOf<String, String>()

    suspend fun getLoginByEmail(phone: String,password: String)=
        RetrofitClient.apiService.getLoginByEmail(phone,password)

    suspend fun getBanner() = RetrofitClient.apiService.getBanner()

    /**
     * 获取歌曲url
     */
    suspend fun getSongUrl(id: Long?)=RetrofitClient.apiService.getSongUrl(id,"lossless")

    /**
     * 获取歌曲详情
     */
    suspend fun getSongDetail(id: Long?)=RetrofitClient.apiService.getSongDetail(id);
}

