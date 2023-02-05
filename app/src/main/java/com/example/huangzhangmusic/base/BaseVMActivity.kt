package com.example.huangzhangmusic.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.huangzhangmusic.base.BaseActivity

abstract class BaseVMActivity<VB : ViewDataBinding,VM:ViewModel> : BaseActivity<VB>() {

    open lateinit var viewModel: VM

    override fun initViewModel() {
        viewModel=ViewModelProvider(this).get(getVMClass())
    }

    abstract fun getVMClass():Class<VM>
}
