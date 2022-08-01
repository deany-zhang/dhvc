package com.dhvc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*

import android.widget.Toast

import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout

import com.dhvc.toalbase.BaseActivity
import com.dhvc.ui_fragment.Home_Fragment
import com.dhvc.ui_fragment.Mine_Fragment

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dhvc.Scan.ScanDelegate
import com.dhvc.Scan.Scan_BlankFragment
import com.dhvc.databinding.TextLayoutBinding
import com.dhvc.ui_activity.ui.Scan_MainActivity
import com.dhvc.ui_fragment.chat_Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions




/**
 * Created by deany-zhang
 * time :
 * explain :
*/
class MainActivity : BaseActivity() ,ScanDelegate{

    override fun observeViewModel() {

    }


    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    override fun initView() {

        homeFragment = Home_Fragment()
        chatFragment = chat_Fragment()
        mineFragment = Mine_Fragment()


        val navController  = Navigation.findNavController(this, R.id.fragment_nav)
        val scanMainactivity = Scan_MainActivity()

        binding?.fab?.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                delay(600)
                val Intent = Intent(this@MainActivity,scanMainactivity::class.java)
                startActivity(Intent)
         }
        }



        binding!!.navBnt.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homefragment -> {
                    navController.navigate(R.id.home)
                }
                R.id.chatfragment -> {

                    navController.navigate(R.id.chat)
                }
                R.id.mindfragment -> {
                    navController.navigate(R.id.mind)
                }

            }
            true
        }

        binding?.navBnt?.let { NavigationUI.setupWithNavController(it,navController) }

        binding?.drawern?.addDrawerListener(object :DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val content: View = binding?.drawern!!.getChildAt(0)
                val scale = 1 - slideOffset //1~0
                content.translationX = drawerView.measuredWidth * (1 - scale) //0~width
            }

            override fun onDrawerOpened(drawerView: View) {


            }

            override fun onDrawerClosed(drawerView: View) {


            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })

        binding?.toolbar?.inflateMenu(R.menu.options)
        val mDrawerToggle =
        ActionBarDrawerToggle(this, binding?.drawern, binding?.toolbar,R.string.nav_app_bar_open_drawer_description,R.string.nav_app_bar_open_drawer_description)
        mDrawerToggle.syncState()
        binding?.drawern?.setDrawerListener(mDrawerToggle)

    }



    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.fragment_nav).navigateUp()
    }

    var binding : TextLayoutBinding ?=null
    override fun initViewBinding() {
         binding = TextLayoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

//    https://github.com/deany-zhang/dhvc.git
//    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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options,menu)
        return super.onCreateOptionsMenu(menu)
    }



    var homeFragment: Home_Fragment?=null
    var chatFragment:chat_Fragment?=null
    var mineFragment: Mine_Fragment?=null


    fun return_home(view: View) {
        finish()
    }

    override fun onStart() {
        super.onStart()
        requestCodeQRCodePermissions()
    }
    private val REQUEST_CODE_QRCODE_PERMISSIONS = 1
    @AfterPermissionGranted(1)
    private fun requestCodeQRCodePermissions() {
        val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (!EasyPermissions.hasPermissions(this, perms[0],perms[1])) {
            EasyPermissions.requestPermissions(this,"扫描二维码需要打开相机和散光灯的权限",
                REQUEST_CODE_QRCODE_PERMISSIONS,perms[0],perms[1])
        }

    }




    override fun onScanQRCodeSuccess(result: String) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show()
//        scanBlankBinding?.onStop()
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.show(homeFragment!!).hide(chatFragment!!)
//        .hide(mineFragment!!).commit()

    }

    override fun onScanQRCodeOpenCameraError() {

    }


}

