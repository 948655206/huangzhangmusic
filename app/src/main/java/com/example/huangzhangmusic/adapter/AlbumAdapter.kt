package com.example.huangzhangmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.huangzhangmusic.databinding.AdapterRecommendSongListBinding
import com.example.huangzhangmusic.domain.Binding
import com.example.huangzhangmusic.domain.NewAlbum

class AlbumAdapter : BaseQuickAdapter<NewAlbum.Album, AlbumAdapter.VH>() {
    class VH(
        parent: ViewGroup,
        val binding: AdapterRecommendSongListBinding = AdapterRecommendSongListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: NewAlbum.Album?) {
        //设置Item数据
        holder.binding.apply {
            items.get(position).apply {
                val picUrl = picUrl
                Glide.with(context).load(picUrl).into(recommendImg)
                recommendTv.text = name
            }
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}
