package com.example.huangzhangmusic.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 需要监听viewModel调用
 */
abstract class BaseVMFragment<VB : ViewDataBinding, VM : ViewModel> : BaseFragment<VB>() {

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //创建ViewModel
        initViewModel()
        setView()
        setEvent()
        //观察数据变化
        setObserver()
    }

    open fun setObserver() {

    }

    open fun setView() {

    }

    open fun setEvent() {

    }


    /**
     * 创建ViewModel
     */
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(getVMClass())
    }

    abstract fun getVMClass(): Class<VM>

}