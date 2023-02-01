package com.example.huangzhangmusic

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.AssetManager

class App : Application() {

    companion object {
        var context:Context ?= null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}