package com.example.huangzhangmusic.activity

import androidx.lifecycle.Observer
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseVMActivity
import com.example.huangzhangmusic.databinding.ActivityLoginBinding
import com.example.huangzhangmusic.domain.LoginState
import com.example.huangzhangmusic.viewModel.login.LoginViewModel


/**
 * 登录页面
 */
class LoginActivity : BaseVMActivity<ActivityLoginBinding,LoginViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getVMClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    //监听数据变化
    override fun initObserver() {
        viewModel.apply {
            loginState.observe(this@LoginActivity, Observer {
                when (it) {
                    LoginState.ONLINE -> {
                        finish()
                    }
                }
            })
        }
    }
}