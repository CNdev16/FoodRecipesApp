package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite

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

class FavoritesMenuViewModel(private val iServiceRepository: IServiceRepository) : BaseViewModel(){

    val allPostsMenu = MutableLiveData<List<ServiceResponse>>()

    fun fetchAllPostsMenu(){
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getAllPostsMenu() }) {
                is UseCaseResult.Success -> {
                    allPostsMenu.postValue(result.data)
                }
                is UseCaseResult.Error -> {
                    Log.d("chok exception", "${result.exception.message}")
                }
            }
        }
    }

}