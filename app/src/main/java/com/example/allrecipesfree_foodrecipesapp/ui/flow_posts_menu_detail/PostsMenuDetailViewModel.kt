package com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail

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

class PostsMenuDetailViewModel (private val iServiceRepository: IServiceRepository) : BaseViewModel(){

    val postsMenuDetail = MutableLiveData<ServiceResponse>()

    fun getPostsMenuDetail(postsId: Int){
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO){iServiceRepository.getPostsMenuDetail(postsId)}){
                is UseCaseResult.Success -> {
                    postsMenuDetail.postValue(result.data)
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }
}