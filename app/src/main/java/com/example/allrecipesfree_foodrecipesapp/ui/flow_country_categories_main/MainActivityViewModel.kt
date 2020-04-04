package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.data.ServiceResponse
import com.example.core.remote.RemoteRepositoryImpl
import com.example.core.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val iServiceRepository: RemoteRepositoryImpl) : BaseViewModel() {

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
                withContext(Dispatchers.IO) { iServiceRepository.getSearchPostsMenu() }) {
                is UseCaseResult.Success -> {
                    allPostsMenuBySearch.postValue(result.data.filter { response -> response.title!!.rendered!!.contains(s) })
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }
}