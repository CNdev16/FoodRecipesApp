package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataUseCase
import com.example.core.usecase.GetCountryCategoriesOnlyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesRecipesViewModel(private val getAllCountryCategoriesOnlyUseCase: GetCountryCategoriesOnlyUseCase): BaseViewModel() {

    val allCountryCategoriesOnlyData = MutableLiveData<List<ResultResponse>>()

    fun getCountryCategoriesOnlyData() {
        showLoading.value = true
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getAllCountryCategoriesOnlyUseCase.execute(Unit) }) {
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
}