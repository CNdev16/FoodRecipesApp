package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories

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

class MenuCategoriesViewModel(private val iServiceRepository: IServiceRepository) :
    BaseViewModel() {

    val allMenuCategories = MutableLiveData<List<ServiceResponse>>()
    val allPostsMenu = MutableLiveData<List<ServiceResponse>>()

    fun fetchMenuCategories(parentNo: Int) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getMenuCategories(parentNo) }) {
                is UseCaseResult.Success -> {
                    allMenuCategories.postValue(result.data)
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }

    fun fetchPostsMenu(categoriesNo: Int) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getPostsMenu(categoriesNo) }) {
                is UseCaseResult.Success -> {
                    allPostsMenu.postValue(result.data)
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }

}