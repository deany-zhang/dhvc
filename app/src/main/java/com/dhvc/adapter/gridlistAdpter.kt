package com.dhvc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.GridLayoutHelper

import com.dhvc.R
import com.dhvc.databinding.ItemListBinding
import com.dhvc.view.PressDownTouchListener


class gridlistAdpter(
    var context: Context?,
    var gridLayoutHelper: GridLayoutHelper?,
   var  title: List<String>
) : DelegateAdapter.Adapter<gridlistAdpter.ViewHolder>() {
    class ViewHolder(dn :ItemListBinding):RecyclerView.ViewHolder(dn.root){
        val dataBinding = dn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val db : ItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_list,parent,false)
        return ViewHolder(db)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pressDownTouchListener = PressDownTouchListener(holder.dataBinding.con)
        holder.dataBinding.con.setOnTouchListener(pressDownTouchListener)
    }

    override fun getItemCount(): Int =6

    override fun onCreateLayoutHelper(): LayoutHelper ? =gridLayoutHelper
}