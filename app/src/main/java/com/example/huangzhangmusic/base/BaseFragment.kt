package com.example.huangzhangmusic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    protected lateinit var mBinding:VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding=DataBindingUtil.inflate<VB>(inflater,getLayoutId(),container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化控件
        initView()
        //添加事件
        initEvent()
        //监听activity中 Observer
        initObserver()
    }

    open fun initObserver() {

    }

    open fun initEvent() {

    }

    open fun initView() {

    }

    abstract fun getLayoutId(): Int
}