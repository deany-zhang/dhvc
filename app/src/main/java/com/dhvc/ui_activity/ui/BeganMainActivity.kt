package com.dhvc.ui_activity.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.dhvc.MainActivity
import com.dhvc.R
import com.dhvc.databinding.ActivityBeganMainBinding
import com.dhvc.toalbase.BaseActivity
import com.dhvc.utils.BaseApi
import com.dhvc.utils.WindowsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BeganMainActivity :BaseActivity() {
    override fun observeViewModel() {

    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun initView() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.property)
        WindowsUtils.setStatusBarColor(window, resources, R.color.F4EFEF)
        GlobalScope.launch(Dispatchers.Main) {
            binding.cons.startAnimation(animation)
            delay(1500)
            binding.cons.visibility = View.GONE
            initVisi()
        }


    }

    private fun initVisi() {
        if (!TextUtils.isEmpty(BaseApi.KEY)){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,Login_MainActivity::class.java)
            startActivity(intent)
        }

    }

    private lateinit var binding :ActivityBeganMainBinding
    override fun initViewBinding() {
         binding = ActivityBeganMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}