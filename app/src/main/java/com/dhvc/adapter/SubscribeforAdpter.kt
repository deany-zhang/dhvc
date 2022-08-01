package com.dhvc.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.dhvc.databinding.TitleBinding

import com.dhvc.R
import com.dhvc.databinding.ItemListBinding
import com.dhvc.databinding.RecipeItemBinding
import com.dhvc.home.DataBeanItem
import com.dhvc.home.HomeViewmodel
import com.dhvc.home.RecipeViewHolder


class SubscribeforAdpter(
    private val homeViewmodel: HomeViewmodel,
    var context: Context?,
    var  list: List<DataBeanItem>
) : RecyclerView.Adapter<RecipeViewHolder>() {



    interface RecyclerItemListener{
    fun onItemSelected(dataBeanItem: DataBeanItem)
    }

    var onItemClickListener: RecyclerItemListener ?=null
    @JvmName("setOnItemClickListener1")
    fun setOnItemClickListener(recyclerItemListener: RecyclerItemListener){
        this.onItemClickListener = recyclerItemListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val db=RecipeItemBinding .inflate(LayoutInflater.from(context),parent,false)
        return RecipeViewHolder(db)
    }

    override fun getItemCount(): Int =list.size
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        context?.let { onItemClickListener?.let { it1 ->
            holder.bind(context = it,list[position],
                it1
            )
        } }
    }

}