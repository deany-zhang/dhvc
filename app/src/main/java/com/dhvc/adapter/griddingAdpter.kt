package com.dhvc.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper
import com.dhvc.databinding.TitleBinding

import com.dhvc.R
import com.dhvc.databinding.TitleRecyclerviewBinding


class griddingAdpter(
    var context: Context?,
    var gridLayoutHelper: SingleLayoutHelper,
    var title: List<String>
) : DelegateAdapter.Adapter<griddingAdpter.ViewHolder>() {
    class ViewHolder(dn :TitleRecyclerviewBinding):RecyclerView.ViewHolder(dn.root){
        val dataBinding = dn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val db : TitleRecyclerviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.title_recyclerview,parent,false)
        return ViewHolder(db)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val linearLayoutManager = GridLayoutManager(context,2)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        holder.dataBinding.recylerview.layoutManager = linearLayoutManager
        val adapter = gridtitl_listAdpter(context = context,title = title)
        holder.dataBinding.recylerview.adapter=adapter

    }

    override fun getItemCount(): Int =1

    override fun onCreateLayoutHelper(): LayoutHelper ? =gridLayoutHelper
}