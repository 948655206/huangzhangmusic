package com.example.huangzhangmusic.activity

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.base.BaseVMActivity
import com.example.huangzhangmusic.databinding.ActivityMusicBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.viewModel.MusicViewModel
import com.lzx.starrysky.StarrySkyInstall
import com.lzx.starrysky.notification.INotification

class MusicActivity : BaseVMActivity<ActivityMusicBinding, MusicViewModel>() {
    override fun getLayoutId(): Int = R.layout.activity_music

    override fun getVMClass(): Class<MusicViewModel> =MusicViewModel::class.java

    override fun initView() {
        viewModel.intentState.postValue(IntentState.BOTTOM)
        StarrySkyInstall
            .init(application)
            .connService(true)
            .setNotificationSwitch(true)
            .setNotificationType(INotification.SYSTEM_NOTIFICATION)
            .apply()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        mBinding.musicBottom.setupWithNavController(
            navHostFragment.navController
        )
    }

    override fun initObserver() {
        viewModel.apply {
            intentState.observe(this@MusicActivity, Observer {
                when (it) {
                    IntentState.BOTTOM -> {
                        mBinding.musicBottom.visibility= View.VISIBLE
                    }
                    IntentState.MUSIC -> {
                        mBinding.musicBottom.visibility= View.GONE
                    }
                }
            })
        }
    }

    override fun initEvent() {

    }

    open fun setIntentState(state: IntentState){
        viewModel.intentState.postValue(state)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")

    }
}