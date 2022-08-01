package com.dhvc.utils

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.dhvc.home.DataBeanItem
import com.dhvc.home.ImageUrlBena
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

class RemoteData(str :String) :RemoteDataSource,ServiceGenerator(str) {
    override suspend fun requestRecipes(s:String,s1:String): loginBean<UserInfo> {
        var map =HashMap<String,String>()
        map["user_name"] = s
        map["user_password"] = s1
        var data = withContext(Dispatchers.IO){
            createService(Apiservice::class.java).post("user/login_formdata.php",map)
        }
      return loginBean(data.status,data.user_info)
    }

    override suspend fun bannerUrl(): ImageUrlBena {
        var data = withContext(Dispatchers.IO){
           createService(Apiservice::class.java).geturl("api/public/v1/home/swiperdata")
        }
        return data
    }

    override suspend fun Data_Home(): ArrayList<DataBeanItem> {
        var data = withContext(Dispatchers.IO){
            createService(Apiservice::class.java).fetchRecipes()
        }
        return data
    }

    override suspend fun homeScan(result: ArrayList<String>): ArrayList<String> {
        return result
    }
}