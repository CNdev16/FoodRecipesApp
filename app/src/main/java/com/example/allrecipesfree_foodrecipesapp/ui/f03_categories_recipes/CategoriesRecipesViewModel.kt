package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.GetCountryCategoryFromLocalUseCase
import com.example.core.usecase.GetCountryCategoryOnlyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesRecipesViewModel(private val getCountryCategoryOnlyUseCase: GetCountryCategoryOnlyUseCase,
                                 private val getCountryCategoryFromLocalUseCase: GetCountryCategoryFromLocalUseCase
): BaseViewModel() {

    val allCountryCategoriesOnlyData = MutableLiveData<List<CountryCategory>>()
    val allDataFromDb = MutableLiveData<List<CountryCategory>>()

    fun getCountryCategoriesOnlyData(isInternetConnected: Boolean) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getCountryCategoryOnlyUseCase.execute(Unit, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    allCountryCategoriesOnlyData.value = result.data
                    showLoading.value = false
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                }
            }
        }
    }

    fun getAllDataFromDb(isInternetConnected: Boolean){
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO){getCountryCategoryFromLocalUseCase.execute(Unit, isInternetConnected)}){
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    allDataFromDb.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                }
            }
        }
    }
}