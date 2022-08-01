package com.dhvc.home

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.dhvc.R
import com.dhvc.adapter.SubscribeforAdpter
import com.dhvc.databinding.RecipeItemBinding
import com.dhvc.utils.TextViewSpanUtil


class RecipeViewHolder(private val itemBinding: RecipeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(context:Context, recipesItem: DataBeanItem, recyclerItemListener: SubscribeforAdpter.RecyclerItemListener) {
        itemBinding.tvCaption.text = recipesItem.description
        itemBinding.tvName.text = recipesItem.name
        itemBinding.rlRecipeItem.setOnClickListener {
            if(it!=null){
                recyclerItemListener.onItemSelected(recipesItem)

            }
        }


    }
}

