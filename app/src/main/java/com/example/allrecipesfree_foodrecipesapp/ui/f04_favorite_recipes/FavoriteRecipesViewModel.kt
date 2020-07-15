package com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.GetCountryCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteRecipesViewModel(private val getCountryCategoryUseCase: GetCountryCategoryUseCase) : BaseViewModel() {

    val allDataFromDb = MutableLiveData<List<CountryCategory>>()

    fun getAllDataFromDb(isInternetConnected: Boolean){
        showLoading.value = true
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO){getCountryCategoryUseCase.execute(Unit, isInternetConnected)}){
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