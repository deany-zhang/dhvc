package com.dhvc.utils

import com.dhvc.home.DataBeanItem
import com.dhvc.home.ImageUrlBena
import com.dhvc.login.Authcode
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean

import okhttp3.ResponseBody
import retrofit2.http.*


interface Apiservice {
    @FormUrlEncoded
    @POST
    suspend fun post(@Url url: String?, @FieldMap map: HashMap<String, String>): loginBean<UserInfo>


    @GET
    suspend fun geturl(@Url url :String) : ImageUrlBena


    @GET("android-test/recipes.json")
    suspend fun fetchRecipes() : ArrayList<DataBeanItem>



    @POST("version2/userInfos/getCaptcha")
    suspend fun getUserInfor():Authcode
}