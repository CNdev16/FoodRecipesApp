package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class InsertRecipePostsToLocalUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<RecipePosts, Long>() {
    override suspend fun execute(
        param: RecipePosts,
        isInternetConnected: Boolean
    ): UseCaseResult<Long> {
        return dataRepository.insertRecipePostsToLocal(param)
    }

}