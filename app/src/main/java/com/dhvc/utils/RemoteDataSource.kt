package com.dhvc.utils

import com.dhvc.home.DataBeanItem
import com.dhvc.home.ImageUrlBena
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import kotlinx.coroutines.flow.Flow

/**
 * Created by AhmedEltaher
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(string: String,s1:String): loginBean<UserInfo>
    suspend fun bannerUrl(): ImageUrlBena
    suspend fun Data_Home(): ArrayList<DataBeanItem>
    suspend fun homeScan(result: ArrayList<String>): ArrayList<String>

}
