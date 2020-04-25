package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.DataRepository
import com.example.core.data.ServiceResponse
import com.example.core.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuCategoriesViewModel(private val dataRepository: DataRepository) :
    BaseViewModel() {

    val allMenuCategories = MutableLiveData<List<ServiceResponse>>()
    val allPostsMenu = MutableLiveData<List<ServiceResponse>>()

//    fun fetchMenuCategories(parentNo: Int) {
//        viewModelScope.launch {
//            when (val result =
//                withContext(Dispatchers.IO) { dataRepository.getMenuCategories(parentNo) }) {
//                is UseCaseResult.Success -> {
//                    allMenuCategories.postValue(result.data)
//                }
//                is UseCaseResult.Error -> {
//
//                }
//            }
//        }
//    }
//
//    fun fetchPostsMenu(categoriesNo: Int) {
//        viewModelScope.launch {
//            when (val result =
//                withContext(Dispatchers.IO) { dataRepository.getPostsMenu(categoriesNo) }) {
//                is UseCaseResult.Success -> {
//                    allPostsMenu.postValue(result.data)
//                }
//                is UseCaseResult.Error -> {
//
//                }
//            }
//        }
//    }

}