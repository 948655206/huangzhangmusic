package com.example.huangzhangmusic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.huangzhangmusic.App
import com.example.huangzhangmusic.R
import com.example.huangzhangmusic.databinding.AdapterChartBinding
import com.example.huangzhangmusic.domain.Banner
import com.youth.banner.adapter.BannerAdapter


@BindingAdapter("imgUrl")
fun setImg(iv:ImageView,imgUrl:String){
    Glide.with(iv.context).load(imgUrl).into(iv)
}

class ChartAdapter(datas: MutableList<Banner>?) : BannerAdapter<Banner, ChartAdapter.InnerHolder>(
    datas
) {

    private val context by lazy {
        App.context
    }

    class InnerHolder(itemView: View, val itemBinding: AdapterChartBinding) :
        RecyclerView.ViewHolder(itemView) {

    }


    fun setData(banners: List<Banner>) {
        mDatas.clear()
        mDatas.addAll(banners)
        notifyDataSetChanged()
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): InnerHolder {
        val itemBinding =DataBindingUtil.inflate<AdapterChartBinding>(LayoutInflater.from(context),R.layout.adapter_chart, parent, false)
        return InnerHolder(itemBinding.root,itemBinding)
    }

    override fun onBindView(holder: InnerHolder?, data: Banner?, position: Int, size: Int) {
        val itemData=mDatas[position]
        holder?.itemBinding?.itemData=itemData
    }

}