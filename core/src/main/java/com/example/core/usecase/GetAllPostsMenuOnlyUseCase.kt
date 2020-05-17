package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetAllPostsMenuOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Unit, List<RecipePosts>>() {
    override suspend fun execute(
        param: Unit,
        isInternetConnected: Boolean
    ): UseCaseResult<List<RecipePosts>> {
        return dataRepository.getAllPostsMenuOnly(isInternetConnected)
    }
}