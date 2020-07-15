package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.usecase.GetRecipePostsUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyFoodsViewModel(private val getRecipePostsUseCase: GetRecipePostsUseCase) :
    BaseViewModel() {

    val thaiRecipeData = MutableLiveData<List<RecipePosts>>()

    fun getThaiRecipePosts(isInternetConnected: Boolean) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) {
                    getRecipePostsUseCase.execute(
                        Unit,
                        isInternetConnected
                    )
                }) {
                is UseCaseResult.Success -> {
                    logD(Gson().toJson(result.data))
                    thaiRecipeData.value = result.data
                    showLoading.value = false
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }
}