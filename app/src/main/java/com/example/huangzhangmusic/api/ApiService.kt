package com.example.huangzhangmusic.api

import com.example.huangzhangmusic.domain.BannerData
import com.example.huangzhangmusic.domain.ResultLogin
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    companion object {
        const val BASE_URL = "http://192.168.3.39:3000/"
    }



    @POST("login")
    suspend fun getLoginByEmail(@Query("email")phone:String,@Query("password") password:String) : ResultLogin

    /**
     * 获取轮播图
     */
    @GET("banner")
    suspend fun getBanner() :BannerData
}