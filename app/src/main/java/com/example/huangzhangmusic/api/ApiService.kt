package com.example.huangzhangmusic.api

import com.example.huangzhangmusic.domain.*
import com.example.huangzhangmusic.utils.LocalIPUtils
import com.example.huangzhangmusic.utils.LocalIPUtils.getLocalIPAddress
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    companion object {
        val BASE_URL = "http://192.168.0.103:3000/"
    }


    @POST("login")
    suspend fun getLoginByEmail(
        @Query("email") phone: String,
        @Query("password") password: String
    ): ResultLogin

    /**
     * 获取轮播图
     */
    @GET("banner")
    suspend fun getBanner(): BannerData

    /**
     * 获取歌曲url
     */
    @GET("song/url/v1")
    suspend fun getSongUrl(@Query("id") id: Long?, @Query("level") level: String): SongUrlData

    /**
     * 获取歌曲详情
     */
    @GET("song/detail")
    suspend fun getSongDetail(@Query("ids") id: Long?): SongDetail

    /**
     * 推荐歌单
     */
    @GET("personalized")
    suspend fun getRecommendSongList(): RecommendSong

    /**
     * 获取最新专辑
     */
    @GET("album/newest")
    suspend fun getNewestAlbum(): NewAlbum
}