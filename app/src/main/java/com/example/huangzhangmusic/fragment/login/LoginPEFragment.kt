package com.example.huangzhangmusic.fragment.login

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.LoginActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.databinding.FragmentLoginPeBinding
import com.example.huangzhangmusic.domain.CodeState
import com.example.huangzhangmusic.domain.LoginData
import com.example.huangzhangmusic.domain.LoadingState
import com.example.huangzhangmusic.domain.LoginWay
import com.example.huangzhangmusic.utils.ToastUtil
import com.example.huangzhangmusic.viewModel.login.LoginViewModel

/**
 * 手机号 或 邮箱号 登录
 */
class LoginPEFragment : BaseFragment<FragmentLoginPeBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(activity as LoginActivity).get(LoginViewModel::class.java)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_login_pe
    }

    override fun initView() {
        val loginWay = viewModel.loginWay.value
        if (loginWay != null) {
            mBinding.model = LoginData(loginWay)
        }
        when (loginWay) {
            LoginWay.Email -> {
                mBinding.sendCode.visibility=View.GONE
            }
            LoginWay.Phone -> {
                mBinding.sendCode.visibility=View.VISIBLE
            }
        }
        when (viewModel.codeState.value) {
            CodeState.OK -> {
                mBinding.sendCode.text = "发送验证码"
            }
            CodeState.NO -> {
                mBinding.sendCode.text = viewModel.currentTime.toString()
            }
        }
    }

    override fun initObserver() {
        viewModel.apply {
            currentTime.observe(requireActivity(), Observer {
                mBinding.sendCode.text = currentTime.value.toString()
            })

            codeState.observe(requireActivity(), Observer {
                mBinding.sendCode.text = "发送验证码"
            })
            mLoadingState.observe(requireActivity(), Observer {
                when (it) {
                    LoadingState.SUCCESS->{
                        ToastUtil.toastShow(ToastUtil.ToastState.SUCCEED)
                    }
                    LoadingState.LOADING->{
                        ToastUtil.toastShow(ToastUtil.ToastState.LOADING)
                    }
                    LoadingState.ERROR->{
                        ToastUtil.toastShowText("账号或密码错误")
                    }
                }
            })
        }
    }


    override fun initEvent() {
        //登录
        mBinding.apply {
            sendCode.setOnClickListener {
                viewModel.sendCode()
            }
            backIb.setOnClickListener {
                clickBack()
            }
            loginBtn.setOnClickListener{
                if (editTextNumber.text.isEmpty()||passwordEt.text.isEmpty()){
                    println("内容不能为空")
                    ToastUtil.toastShowText("内容不能为空")

                }else{
                    viewModel.login(editTextNumber.text.toString(),passwordEt.text.toString())
                }
            }
        }
    }

    //点击回退
    private fun clickBack() {
        findNavController().navigateUp()
    }

}