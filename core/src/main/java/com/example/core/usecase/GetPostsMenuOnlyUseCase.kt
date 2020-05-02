package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.Posts
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetPostsMenuOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Int, List<Posts>>() {
    override suspend fun execute(param: Int): UseCaseResult<List<Posts>> {
        return dataRepository.getPostsMenuOnly(param)
    }
}