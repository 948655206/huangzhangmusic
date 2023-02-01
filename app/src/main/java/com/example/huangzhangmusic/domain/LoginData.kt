package com.example.huangzhangmusic.domain

/**
 * 登录方式
 */
data class LoginData(val loginWay: LoginWay) {
    fun setLoginTile():String {
        return when (loginWay) {
            LoginWay.Phone -> {
                "请输入手机号"
            }
            LoginWay.Email -> {
                "请输入163邮箱号"
            }
        }
    }

    fun setLoginHintPassword():String{
        return when (loginWay) {
            LoginWay.Phone -> {
                "请输入验证码"

            }
            LoginWay.Email -> {
                "请输入密码"
            }
        }
    }

    //设置提示字符
    fun setLoginHint():String{
        return when (loginWay) {
            LoginWay.Phone -> {
                "手机号"
            }
            LoginWay.Email -> {
                "163邮箱号"
            }
        }
    }


}

//验证码 是否可以发送
enum class CodeState{
    OK,NO
}

enum class LoginWay {
    Email, Phone
}

enum class LoginState{
    ONLINE,OFFLINE
}

enum class LoadingState{
    LOADING,
    SUCCESS,
    ERROR
}