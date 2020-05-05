package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataUseCase
import com.example.core.usecase.GetAllPostsMenuOnlyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllRecipesViewModel(
    private val getAllPostsMenuOnlyUseCase: GetAllPostsMenuOnlyUseCase
) : BaseViewModel() {

    val allPostsMenuOnlyData = MutableLiveData<List<Posts>>()

    fun getAllPostsMenuOnlyData() {
        showLoading.value = true
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getAllPostsMenuOnlyUseCase.execute(Unit) }) {
                is UseCaseResult.Success -> {
                    allPostsMenuOnlyData.value = result.data
                    showLoading.value = false
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                }
            }
        }
    }

}