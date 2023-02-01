package com.example.huangzhangmusic.fragment.login

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.LoginActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.base.BaseVMFragment
import com.example.huangzhangmusic.databinding.FragmentLgoinHostBinding
import com.example.huangzhangmusic.domain.LoginWay
import com.example.huangzhangmusic.viewModel.login.LoginViewModel

/**
 * 登录页面主页
 */
class LoginHostFragment : BaseFragment<FragmentLgoinHostBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(activity as LoginActivity).get(LoginViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_lgoin_host
    }

    override fun initEvent() {
        val bundle = Bundle()
        mBinding.loginPhoneBtn.setOnClickListener {
            viewModel.loginWay.value=LoginWay.Phone
            findNavController().navigate(R.id.to_loginPEFragment,bundle)
        }
        mBinding.loginEmailBtn.setOnClickListener {
            viewModel.loginWay.value=LoginWay.Email
            findNavController().navigate(R.id.to_loginPEFragment,bundle)
        }
    }


}