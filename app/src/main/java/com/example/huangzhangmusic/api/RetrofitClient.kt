package com.example.huangzhangmusic.api

import com.example.huangzhangmusic.api.ApiService.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit


object RetrofitClient {
    val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(30,TimeUnit.SECONDS)
        .build()

    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService=retrofit.create(ApiService::class.java)

}



