package com.example.huangzhangmusic.fragment


import android.content.res.Configuration
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.activity.MusicActivity
import com.example.huangzhangmusic.base.BaseActivity
import com.example.huangzhangmusic.base.BaseFragment
import com.example.huangzhangmusic.base.BaseVMFragment
import com.example.huangzhangmusic.databinding.FragmentPlayBinding
import com.example.huangzhangmusic.domain.IntentState
import com.example.huangzhangmusic.domain.SongDetail
import com.example.huangzhangmusic.utils.GlideUtils
import com.example.huangzhangmusic.viewModel.PlayViewModel

class PlayFragment : BaseVMFragment<FragmentPlayBinding, PlayViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_play
    }

    override fun setView() {
        println("初始化...")
        mBinding.downBtn.setOnClickListener {
            findNavController().navigateUp()
            (requireActivity() as MusicActivity).setIntentState(IntentState.BOTTOM)
        }
    }

    override fun initObserver() {
        viewModel.songData.observe(this,Observer{

        })
        viewModel.songDetail.observe(this, Observer {
            mBinding.apply {
                for (song in it.songs) {
                    GlideUtils.getInstance().setImageSrc(song.al.picUrl,playImg)
                }
            }
        })
    }

    override fun setEvent() {
        //单曲Id
        val targetId = arguments?.getLong("targetId")
        if (targetId != null) {
            viewModel.apply {
                openSingUrl(targetId)
                getSongDetail(targetId)
            }
        }else{
            println("targetId为空..")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy123")
    }

    override fun getVMClass(): Class<PlayViewModel> {
        return PlayViewModel::class.java
    }
}