package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetAllPostsMenuOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Unit, List<Posts>>() {
    override suspend fun execute(param: Unit): UseCaseResult<List<Posts>> {
        return dataRepository.getAllPostsMenuOnly()
    }
}