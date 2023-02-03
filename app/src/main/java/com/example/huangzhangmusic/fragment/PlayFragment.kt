package com.example.huangzhangmusic.fragment


import android.content.res.Configuration
import android.view.Window
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.MusicActivity
import com.example.huangzhangmusic.base.BaseActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.databinding.FragmentPlayBinding
import com.example.huangzhangmusic.domain.IntentState

class PlayFragment :BaseFragment<FragmentPlayBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_play
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun initView() {
        println("初始化...")
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mBinding.downBtn.setOnClickListener{
            findNavController().navigateUp()
            (requireActivity() as MusicActivity).setIntentState(IntentState.BOTTOM)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy123")
    }
}