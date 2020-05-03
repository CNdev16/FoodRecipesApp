package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.data.ServiceResponse
import com.example.core.UseCaseResult
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.example.core.data.SubCate
import com.example.core.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val getAllCountryCategoriesOnlyUseCase: GetCountryCategoriesOnlyUseCase,
    private val getAllPostsMenuOnlyUseCase: GetAllPostsMenuOnlyUseCase
) : BaseViewModel() {

    val allPostsMenuOnlyData = MutableLiveData<List<Posts>>()
    val allCountryCategoriesOnlyData = MutableLiveData<List<ResultResponse>>()

    fun getAllPostsMenuOnlyData() {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getAllPostsMenuOnlyUseCase.execute(Unit) }) {
                is UseCaseResult.Success -> allPostsMenuOnlyData.value = result.data
                is UseCaseResult.Error -> {
                }
            }
        }
    }

    fun getCountryCategoriesOnlyData() {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getAllCountryCategoriesOnlyUseCase.execute(Unit) }) {
                is UseCaseResult.Success -> allCountryCategoriesOnlyData.value = result.data
                is UseCaseResult.Error -> {
                }
            }
        }
    }
}