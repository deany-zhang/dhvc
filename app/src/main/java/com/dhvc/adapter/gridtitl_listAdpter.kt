package com.dhvc.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
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
import com.dhvc.view.PressDownTouchListener





class gridtitl_listAdpter(
    var context: Context?,
   var  title: List<String>
) : RecyclerView.Adapter<gridtitl_listAdpter.ViewHolder>() {
    class ViewHolder(dn :TitleBinding):RecyclerView.ViewHolder(dn.root){
        val dataBinding = dn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val db : TitleBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.title,parent,false)
        return ViewHolder(db)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.tvTitle.text = title[position]
        val pressDownTouchListener = PressDownTouchListener(holder.dataBinding.cons)
        pressDownTouchListener.setOnClickListener {  }
        holder.dataBinding.cons.setOnTouchListener(pressDownTouchListener)
//        holder.dataBinding.tvTitle.text=title[position]
//        val animY1 = ObjectAnimator.ofFloat(holder.dataBinding.image, "translationY", 0f, 150f, 0f)
//        val animX1 = ObjectAnimator.ofFloat(holder.dataBinding.image, "translationX", 100f, -150f, 0f)
//
//        val animY2 = ObjectAnimator.ofFloat(holder.dataBinding.image, "translationY", 0f, -150f, 0f)
//        val animX2 = ObjectAnimator.ofFloat(holder.dataBinding.image, "translationX", 0f, -150f, 0f)
//
//        val animatorSet1 = AnimatorSet()
//        val animatorSet2 = AnimatorSet()
//        animatorSet1.interpolator = LinearInterpolator()
//        animatorSet2.interpolator = LinearInterpolator()
//        animatorSet1.playTogether(animY1,animX1)
//        animatorSet2.playTogether(animY2,animX2)
//        animatorSet1.duration=10000
//        animatorSet2.duration=10000

//        animatorSet1.addListener(object : AnimatorListenerAdapter(){
//            override fun onAnimationPause(animation: Animator?) {
//                super.onAnimationPause(animation)
//                animatorSet2.start()
//            }
//        })
//
//
//        animatorSet2.addListener(object : AnimatorListenerAdapter(){
//            override fun onAnimationPause(animation: Animator?) {
//                super.onAnimationPause(animation)
//                animatorSet1.start()
//            }
//        })
//        animatorSet1.addPauseListener(object : AnimatorListenerAdapter(){
//            override fun onAnimationCancel(animation: Animator?) {
//                super.onAnimationCancel(animation)
//                animatorSet2.start()
//                Toast.makeText(context,"wan",Toast.LENGTH_LONG).show()
//            }
//        })
//
//        animatorSet2.addPauseListener(object :AnimatorListenerAdapter(){
//            override fun onAnimationCancel(animation: Animator?) {
//                super.onAnimationCancel(animation)
//                animatorSet1.start()
//            }
//        })
//        animatorSet1.start()
    }
    interface ItemListener{
        fun OnItemSelected(s:String )
    }

    override fun getItemCount(): Int =title.size

}