package com.dhvc.login

import androidx.annotation.VisibleForTesting
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhvc.utils.Apiservice
import com.dhvc.utils.DataRepository
import com.dhvc.utils.ServiceGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class LoginViewmodel @Inject  constructor(private var dataRepository: DataRepository) :ViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<loginBean<UserInfo>>()
    val loginLiveData: LiveData<loginBean<UserInfo>> get() = loginLiveDataPrivate


 @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val autocodeLiveDataPrivate = MutableLiveData<Authcode>()
    val codeLiveData: LiveData<Authcode> get() = autocodeLiveDataPrivate

   fun getlogin(s1 :String, s2:String){

       viewModelScope.launch {
           dataRepository.login(s1,s2).collect {
               loginLiveDataPrivate.value = it
           }
       }
   }


   fun getcode(){
       viewModelScope.launch {
          var data = withContext(Dispatchers.IO){
              ServiceGenerator("https://xzykt.longmenshuju.com/")
                  .createService(Apiservice::class.java).getUserInfor()
          }
           autocodeLiveDataPrivate.postValue(data)
       }
   }
}