package com.dhvc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper
import com.bumptech.glide.Glide

import com.dhvc.R
import com.dhvc.databinding.StaggereListBinding
import com.dhvc.home.DataBeanItem
import com.dhvc.utils.TextViewSpanUtil
import com.dhvc.view.PressDownTouchListener


class staggereAdpter(
    var context: Context?,
    var gridLayoutHelper: StaggeredGridLayoutHelper,
    var title: ArrayList<DataBeanItem>
) : DelegateAdapter.Adapter<staggereAdpter.ViewHolder>() {
    class ViewHolder(dn :StaggereListBinding):RecyclerView.ViewHolder(dn.root){
        val dataBinding = dn }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val db : StaggereListBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.staggere_list,parent,false)
        return ViewHolder(db)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.tvTitle.text = title[position].headline
        context?.let {
            Glide.with(it).load(title[position].image).placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.dataBinding.image)}
        val pressDownTouchListener = PressDownTouchListener(holder.dataBinding.con)
        holder.dataBinding.con.setOnTouchListener(pressDownTouchListener)

    }



    override fun getItemCount(): Int =title.size

    override fun onCreateLayoutHelper(): StaggeredGridLayoutHelper ? =gridLayoutHelper
}