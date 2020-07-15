package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetRecipePostsDetailsUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Int, RecipePosts>() {
    override suspend fun execute(
        param: Int,
        isInternetConnected: Boolean
    ): UseCaseResult<RecipePosts> {
        return dataRepository.getRecipePostsDetails(param, isInternetConnected)
    }
}