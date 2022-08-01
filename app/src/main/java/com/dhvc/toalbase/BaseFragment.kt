package com.dhvc.toalbase

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB:ViewDataBinding>(var layoutId:Int) : Fragment(){
    protected lateinit var mDataBinding : DB
    private var isHidden: Boolean? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        initView(savedInstanceState)
        observeViewModel()
        setHasOptionsMenu(true)
        return mDataBinding.root
    }
    abstract fun observeViewModel()
    abstract fun initView(savedInstanceState: Bundle?)

}