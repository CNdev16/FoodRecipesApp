package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetPostsMenuOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Int, List<RecipePosts>>() {
    override suspend fun execute(
        param: Int,
        isInternetConnected: Boolean
    ): UseCaseResult<List<RecipePosts>> {
        return dataRepository.getPostsMenuOnly(param, isInternetConnected)
    }
}