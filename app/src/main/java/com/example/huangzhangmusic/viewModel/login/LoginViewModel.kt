package com.example.huangzhangmusic.viewModel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huangzhangmusic.domain.*
import com.example.huangzhangmusic.repository.Repository
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.util.*

class LoginViewModel : ViewModel() {

    val loginWay = MutableLiveData<LoginWay>()

    val mLoadingState = MutableLiveData<LoadingState>()

    val codeState = MutableLiveData<CodeState>()

    val currentTime = MutableLiveData<Int>()

    val loginState =MutableLiveData<LoginState>()

    private val mRepository by lazy {
        Repository()
    }

    companion object{
        const val DEFAULT_TIME= 60 //默认时间为60s
    }

    /**
     * 登录
     */
    open fun login(phone: String, password: String) {
        mLoadingState.value = LoadingState.LOADING
        when (loginWay.value) {
            LoginWay.Email -> {
                viewModelScope.launch {
                    val resultLogin : ResultLogin = mRepository.getLoginByEmail(phone, password)
                    println("resultLogin==>$resultLogin")
                    if (resultLogin.code==HttpURLConnection.HTTP_OK) {
                        mLoadingState.postValue(LoadingState.SUCCESS)
                        loginState.postValue(LoginState.ONLINE)
                    }else{
                        mLoadingState.postValue(LoadingState.ERROR)
                    }
                }
            }
        }
    }

    //开始验证码
    open fun sendCode() {
        if (codeState.value==CodeState.NO){
            return
        }
        currentTime.postValue(DEFAULT_TIME)
        codeState.postValue(CodeState.NO)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (currentTime.value == 0) {
                    codeState.postValue(CodeState.OK)
                    println("到这里了")
                    cancel()
                }else{
                    if (currentTime.value != null) {
                        currentTime.postValue(currentTime.value!! - 1)
                    }
                    println("当前时间 ${currentTime.value}")
                }

            }
        }, 0,1000)
    }

}