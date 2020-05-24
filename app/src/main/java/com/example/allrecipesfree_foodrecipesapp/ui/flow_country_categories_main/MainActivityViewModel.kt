package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts
import com.example.core.usecase.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val getAllDataUseCase: GetAllDataUseCase,
                            private val insertCountryCategoryToLocalUseCase: InsertCountryCategoryToLocalUseCase,
                            private val insertMenuCategoryToLocalUseCase: InsertMenuCategoryToLocalUseCase,
                            private val insertRecipePostsToLocalUseCase: InsertRecipePostsToLocalUseCase
) : BaseViewModel() {

    val allDataFromService = MutableLiveData<List<CountryCategory>>()
    val insertCountryToLocal = MutableLiveData<Long>()
    val insertMenuToLocal = MutableLiveData<Long>()
    val insertRecipeToLocal = MutableLiveData<Long>()

    val version = MutableLiveData<String>(BuildConfig.VERSION_NAME)

    fun getAllDataFromService(isInternetConnected: Boolean) {
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

    fun insertCountryData(countryCategory: CountryCategory, isInternetConnected: Boolean) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { insertCountryCategoryToLocalUseCase.execute(countryCategory, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    insertCountryToLocal.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }

    fun insertMenuData(menuCategory: MenuCategory, isInternetConnected: Boolean) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { insertMenuCategoryToLocalUseCase.execute(menuCategory, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    insertMenuToLocal.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }

    fun insertRecipeData(recipePosts: RecipePosts, isInternetConnected: Boolean) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { insertRecipePostsToLocalUseCase.execute(recipePosts, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    insertRecipeToLocal.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }

}