package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.usecase.GetCountryCategoryUseCase
import com.example.core.usecase.GetMenuCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesRecipesViewModel(
    private val getCountryCategoryUseCase: GetCountryCategoryUseCase,
    private val getMenuCategoryUseCase: GetMenuCategoryUseCase
) : BaseViewModel() {

    val allCountryCategoryData = MutableLiveData<List<CountryCategory>>()
    val menuCategoryData = MutableLiveData<List<MenuCategory>>()

    fun getCountryCategory(isInternetConnected: Boolean) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) {
                    getCountryCategoryUseCase.execute(
                        Unit,
                        isInternetConnected
                    )
                }) {
                is UseCaseResult.Success -> {
                    allCountryCategoryData.value = result.data
                    showLoading.value = false
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                }
            }
        }
    }

    fun getMenuCategory(countryCateId: Int, isInternetConnected: Boolean) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) {
                getMenuCategoryUseCase.execute(
                    countryCateId,
                    isInternetConnected
                )
            }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    menuCategoryData.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                }
            }
        }
    }
}