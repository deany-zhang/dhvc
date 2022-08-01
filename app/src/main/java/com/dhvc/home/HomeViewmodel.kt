package com.dhvc.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhvc.login.UserInfo
import com.dhvc.login.loginBean
import com.dhvc.utils.DataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewmodel constructor(private val dataRepository: DataRepository):ViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val dataLiveDataPrivate = MutableLiveData<ArrayList<DataBeanItem>>()
    val dataLiveProvd: LiveData<ArrayList<DataBeanItem>> get() = dataLiveDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openRecipeDetailsPrivate = MutableLiveData<DataBeanItem>()
    val openRecipeDetails: LiveData<DataBeanItem> get() = openRecipeDetailsPrivate


    fun data(){
        val coroutineExceptionHandler = CoroutineExceptionHandler{_,ex,->
            "Emmm,服务器出了点小差"+ex.message
            Log.e("TAG", "data:11111 "+ex.message)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            dataRepository.home_data().collect {
                dataLiveDataPrivate.value = it
            }
        }
    }

    fun openRecipeDetails(dataBeanItem: DataBeanItem){
        openRecipeDetailsPrivate.value=dataBeanItem
    }

}