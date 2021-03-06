package com.ftech.dev.android_my_food.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel  : ViewModel(){

    val stateNavigationBotstom = MutableLiveData(false)

    val stateLoading = MutableLiveData(false)

    fun showLoading() {
        stateLoading.value = true
    }

    fun hideLoading(){
        stateLoading.value = false

    }
}