package com.example.huangzhangmusic.fragment

import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.databinding.FragmentPlayBinding

class PlayFragment :BaseFragment<FragmentPlayBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_play
    }

}