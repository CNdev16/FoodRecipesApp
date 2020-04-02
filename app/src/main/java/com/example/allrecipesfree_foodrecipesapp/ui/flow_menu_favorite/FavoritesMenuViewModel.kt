package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.data.ServiceResponse
import com.example.core.RemoteRepository
import com.example.core.RemoteRepositoryImpl
import com.example.core.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesMenuViewModel(private val iServiceRepository: RemoteRepositoryImpl) : BaseViewModel(){

    val allPostsMenu = MutableLiveData<List<ServiceResponse>>()

    fun fetchAllPostsMenu(){
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getAllPostsMenu() }) {
                is UseCaseResult.Success -> {
                    allPostsMenu.postValue(result.data)
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }

}