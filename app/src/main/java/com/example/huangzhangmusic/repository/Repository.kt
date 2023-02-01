package com.example.huangzhangmusic.repository

import com.example.huangzhangmusic.api.RetrofitClient
import java.util.*

class Repository {

    private val mapString= mutableMapOf<String, String>()

    suspend fun getLoginByEmail(phone: String,password: String)=
        RetrofitClient.apiService.getLoginByEmail(phone,password)

    suspend fun getBanner() = RetrofitClient.apiService.getBanner()

}

