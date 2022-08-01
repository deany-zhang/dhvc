package com.dhvc.ui_activity.ui

import android.content.Intent
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.navigation.ui.AppBarConfiguration
import com.dhvc.MainActivity
import com.dhvc.R
import com.dhvc.databinding.ActivityLoginMainBinding
import com.dhvc.databinding.LoginActivityBinding
import com.dhvc.login.LoginViewmodel
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import com.dhvc.toalbase.BaseActivity
import com.dhvc.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import com.dhvc.Scan.Scan_BlankFragment
import com.dhvc.login.Authcode


class Login_MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: LoginActivityBinding
    private val loginmo = LoginViewmodel(DataRepository(RemoteData("http://demo.eolink.com/")))
//    private val mode : LoginViewmodel  by viewModels()
    override fun observeViewModel() {

        observe(loginmo.loginLiveData,::handleLoginResult)
        observe(loginmo.codeLiveData,::handleauthcodResult)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun initView() {
        loginmo.getcode()
        binding.bntLogin.setOnClickListener {
                login()
        }
        binding.imageRefresh.setOnClickListener {
            loginmo.getcode()
        }

    }


     fun handleLoginResult(status: loginBean<UserInfo>) {
         binding.loaderView.visibility=View.GONE
         val intent = Intent(this,MainActivity::class.java)
         startActivity(intent)
    }



     fun handleauthcodResult(authcode: Authcode) {
         val token: ByteArray = Base64.decode(authcode.data.capToken, Base64.DEFAULT)
         val decode: ByteArray = Base64.decode(authcode.data.img, Base64.DEFAULT)
         val bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.size)
         binding.imageAuthcode.setImageBitmap(bitmap)
    }

    fun login(){
            binding.loaderView.visibility=View.VISIBLE
            loginmo.getlogin(
            binding.username.text.toString(),
            binding.password.text.toString())
    }

    override fun initViewBinding() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}