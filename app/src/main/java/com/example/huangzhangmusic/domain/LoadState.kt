package com.example.huangzhangmusic.domain

enum class LoadState {
    SUCCESS,
    LOADING,

    LOADING_MORE_LOADING,//正在加载更多
    LOADING_MORE_EMPTY,//加载更多为空
    LOADING_MORE_ERROR,//加载更多失败

    ERROR,
}