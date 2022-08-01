package com.dhvc.utils


import androidx.hilt.lifecycle.ViewModelInject
import com.dhvc.home.DataBeanItem
import com.dhvc.home.ImageUrlBena
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository  constructor(private val remoteData: RemoteData):DataRepositorySource {
    override suspend fun login(string: String, string1: String): Flow<loginBean<UserInfo>> {
        return flow { emit(remoteData.requestRecipes(string,string1)) }.flowOn(Dispatchers.Default)
    }

    override suspend fun banner(): Flow<ImageUrlBena> {
        return flow { emit(remoteData.bannerUrl()) }.flowOn(Dispatchers.Default)
    }

    override suspend fun home_data(): Flow<ArrayList<DataBeanItem>>{
        return flow { emit(remoteData.Data_Home()) }.flowOn(Dispatchers.Default)
    }

    override suspend fun homeScan(result: ArrayList<String>): Flow<ArrayList<String>> {
        return flow { emit(remoteData.homeScan(result = result)) }.flowOn(Dispatchers.Default)
    }


}