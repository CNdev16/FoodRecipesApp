package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.GetAllDataFromDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyFoodsViewModel(private val getAllDataFromDbUseCase: GetAllDataFromDbUseCase) : BaseViewModel() {

    val allDataFromDb = MutableLiveData<List<CountryCategory>>()

    fun getAllDataFromDb(isInternetConnected: Boolean){
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO){getAllDataFromDbUseCase.execute(Unit, isInternetConnected)}){
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    allDataFromDb.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }
}