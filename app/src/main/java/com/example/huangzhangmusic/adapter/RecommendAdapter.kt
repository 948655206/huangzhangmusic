package com.example.huangzhangmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.QuickAdapterHelper
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.databinding.AdapterRecommendSongListBinding
import com.example.huangzhangmusic.domain.RecommendSong

class RecommendAdapter : BaseQuickAdapter<RecommendSong.Result, RecommendAdapter.VH>() {
    class VH(
        parent: ViewGroup,
        val binding: AdapterRecommendSongListBinding = AdapterRecommendSongListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: RecommendSong.Result?) {
        //设置item数据
        holder.binding.apply {
            items.get(position).apply {
                Glide.with(context).load(picUrl).into(recommendImg)

                recommendTv.text=name
            }


        }
    }



    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}