package com.dhvc.adapter

import android.content.Context
import android.graphics.Outline
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dhvc.R
import com.dhvc.databinding.BannerBinding
import com.dhvc.databinding.TitleBinding
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class BannerSingAdapter constructor(var singleLayoutHelper: SingleLayoutHelper,var list: List<String>,var context: Context) :DelegateAdapter.Adapter<BannerSingAdapter.ViewHolder>() {
    class ViewHolder(dn : BannerBinding): RecyclerView.ViewHolder(dn.root){
        val dataBinding = dn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val db : BannerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.banner_,parent,false)
        return ViewHolder(db)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.banner.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(p0: View?, p1: Outline?) {
                p1?.setRoundRect(0,0,p0!!.width,p0!!.height,30f)
            }

        }

        holder.dataBinding.banner.clipToOutline=true
        holder.dataBinding.banner?.setImages(list)
        holder.dataBinding.banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        holder.dataBinding.banner?.setIndicatorGravity(BannerConfig.CENTER)
        holder.dataBinding.banner?.setImageLoader(object : ImageLoader(){
            override fun displayImage(
                context: Context?,
                path: Any?,
                imageView: ImageView?
            ) {
                if (imageView != null) {
                    if (context != null) {
                        Glide.with(context)
                            .load(path).apply(RequestOptions.bitmapTransform(RoundedCorners(70))).into(imageView)
                    }
                }
            }

        })
        holder.dataBinding.banner?.setBannerAnimation(Transformer.Default)
        holder.dataBinding.banner?.isAutoPlay(true)
        holder.dataBinding.banner?.setDelayTime(1000)
        holder.dataBinding.banner?.start() }


    override fun getItemCount(): Int = 1

    override fun onCreateLayoutHelper(): LayoutHelper=singleLayoutHelper
}