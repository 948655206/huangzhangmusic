package com.example.huangzhangmusic.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        //创建ViewModel
        initViewModel()
        //观察数据变化
        initObserver()
        //初始化 控件
        initView()
        //设置相关事件
        initEvent()
        //开始去加载数据
        startLoadData()
    }

    open fun initViewModel() {

    }

    open fun initObserver() {

    }

    open fun startLoadData() {

    }

    open fun initEvent() {

    }

    open fun initView() {

    }

    abstract fun getLayoutId(): Int

}