package com.example.allrecipesfree_foodrecipesapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope{

    var showLoading = MutableLiveData<Boolean>(true)

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}