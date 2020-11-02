package com.example.allrecipesfree_foodrecipesapp.ui.f05_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.GetAllDataUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchAllRecipesViewModel (private val getAllDataUseCase: GetAllDataUseCase) : BaseViewModel(){

    val allDataFromService = MutableLiveData<List<CountryCategory>>()

    fun getAllDataFromService(isInternetConnected: Boolean) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getAllDataUseCase.execute(Unit, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    logD("result.data ${Gson().toJson(result.data)}")
                    showLoading.value = false
                    allDataFromService.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }
}