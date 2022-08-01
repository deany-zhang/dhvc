package com.dhvc.utils

import com.dhvc.home.DataBeanItem
import com.dhvc.home.ImageUrlBena
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface DataRepositorySource {
    suspend fun login(string: String,string1: String): Flow<loginBean<UserInfo>>
    suspend fun banner(): Flow<ImageUrlBena>
    suspend fun home_data(): Flow<ArrayList<DataBeanItem>>
    suspend fun homeScan(result: ArrayList<String>): Flow<ArrayList<String>>
}