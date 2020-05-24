package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class DeleteRecipePostsFromLocalUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<RecipePosts, Int>() {
    override suspend fun execute(
        param: RecipePosts,
        isInternetConnected: Boolean
    ): UseCaseResult<Int> {
        return dataRepository.deleteRecipePostsFromLocal(param)
    }
}