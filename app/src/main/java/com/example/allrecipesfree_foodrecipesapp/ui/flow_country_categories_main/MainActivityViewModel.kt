package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.network.IServiceRepository
import com.example.allrecipesfree_foodrecipesapp.network.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val iServiceRepository: IServiceRepository) : BaseViewModel() {

    val allCountryCategories = MutableLiveData<List<ServiceResponse>>()
    val allPostsMenuBySearch = MutableLiveData<List<ServiceResponse>>()

    fun fetchCountryCategories(parentNo: Int) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getCountryCategories(parentNo) }) {
                is UseCaseResult.Success -> {
                    allCountryCategories.postValue(result.data)
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }

    fun searchPostsMenu(s: String) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getSearchPostsMenu(s) }) {
                is UseCaseResult.Success -> {
                    allPostsMenuBySearch.postValue(result.data)
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }
}