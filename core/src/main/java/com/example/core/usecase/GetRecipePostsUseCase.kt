package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetRecipePostsUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Any, List<RecipePosts>>() {
    override suspend fun execute(
        param: Any,
        isInternetConnected: Boolean
    ): UseCaseResult<List<RecipePosts>> {
        return if (param is Int) {
            dataRepository.getRecipePostsByMenuCategoryId(param, isInternetConnected)
        } else {
            dataRepository.getAllRecipePosts(isInternetConnected)
        }
    }
}