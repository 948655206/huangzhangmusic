package com.example.huangzhangmusic.utils

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.huangzhangmusic.App
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.utils.ToastUtil.context

object ToastUtil {

    enum class ToastState {
        // 成功
        SUCCEED,

        // 错误
        ERROR,

        // 加载中
        LOADING,

        // 警告
        WARNING;
    }

    private val context by lazy {
        App.context
    }

    private val toast by lazy {
        Toast(context)
    }

    open fun toastShow(toastState: ToastState) {
        var view: View? = null
        when (toastState) {
            ToastState.SUCCEED -> {
                view = LayoutInflater.from(context).inflate(R.layout.toast_success, null)
            }
            ToastState.LOADING -> {
                view = LayoutInflater.from(context).inflate(R.layout.toast_loading, null)
            }
            ToastState.ERROR -> {
                view = LayoutInflater.from(context).inflate(R.layout.toast_error, null)

            }
            ToastState.WARNING -> {
                view = LayoutInflater.from(context).inflate(R.layout.toast_loading, null)
            }
        }
        toast.view = view
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    open fun toastShowText(text: String) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }
}