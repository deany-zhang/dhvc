package com.dhvc.ui_activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import com.dhvc.R
import com.dhvc.databinding.ActivityDetailsMainBinding
import com.dhvc.home.DataBeanItem
import com.dhvc.toalbase.BaseActivity
import com.dhvc.utils.TextViewSpanUtil
import androidx.appcompat.app.ActionBar


class DetailsMainActivity : BaseActivity() {
    override fun observeViewModel() {
    }

    override fun initView() {
        val parcelable = intent?.getParcelableExtra<DataBeanItem>("data")
        mDataBinding?.toolbar?.title ="详情页面"
        mDataBinding?.toolbar?.subtitle =parcelable?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(mDataBinding?.toolbar)
        val isExpandDescripe = booleanArrayOf(false)
        TextViewSpanUtil.toggleEllipsize(
            this,
            mDataBinding?.tvDescription,
            1,
            parcelable?.description,
            "展开全文",
            R.color.black, isExpandDescripe[0])

        mDataBinding?.tvDescription?.setOnClickListener {

            if (isExpandDescripe[0]) {
                isExpandDescripe[0] = false
                mDataBinding?.tvDescription!!.maxLines = 1// 收起
            } else {
                isExpandDescripe[0] = true
                mDataBinding!!.tvDescription.maxLines = Int.MAX_VALUE// 收起
            }

            TextViewSpanUtil.toggleEllipsize(
                this,
                mDataBinding?.tvDescription,
                1,
                parcelable?.description,
                "展开全文",
                R.color.black, isExpandDescripe[0])
        }
        mDataBinding?.data=parcelable
        if (parcelable?.description!=null){
            mDataBinding?.pbLoading?.visibility= View.GONE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    var mDataBinding : ActivityDetailsMainBinding?=null
    override fun initViewBinding() {
        mDataBinding = ActivityDetailsMainBinding.inflate(layoutInflater)
        setContentView(mDataBinding!!.root)
    }

}