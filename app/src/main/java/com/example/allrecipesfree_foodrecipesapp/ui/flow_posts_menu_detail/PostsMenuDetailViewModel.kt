package com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.DataRepository
import com.example.core.data.ServiceResponse
import com.example.core.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsMenuDetailViewModel (private val dataRepository: DataRepository) : BaseViewModel(){

    val postsMenuDetail = MutableLiveData<ServiceResponse>()

    fun getPostsMenuDetail(postsId: Int){
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO){dataRepository.getPostsMenuDetail(postsId)}){
                is UseCaseResult.Success -> {
                    postsMenuDetail.postValue(result.data)
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }
}