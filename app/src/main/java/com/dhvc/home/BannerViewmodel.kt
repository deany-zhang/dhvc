package com.dhvc.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhvc.utils.DataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BannerViewmodel  constructor(private val dataRepository: DataRepository) :ViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val bannderDataPrivate=MutableLiveData<ImageUrlBena>()
    val bannerLiveData : LiveData<ImageUrlBena> get() = bannderDataPrivate

    fun banner(){
        viewModelScope.launch {
            dataRepository.banner().collect {
                bannderDataPrivate.value=it
            }
        }
    }
}