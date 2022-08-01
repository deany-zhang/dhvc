package com.dhvc.toalbase

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dhvc.R

/**
 * Created by deany-zhang
 * time :
 * explain :
*/

abstract class BaseActivity : AppCompatActivity() {

    abstract fun observeViewModel()
    abstract fun initView()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
         initView()
        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }



}
