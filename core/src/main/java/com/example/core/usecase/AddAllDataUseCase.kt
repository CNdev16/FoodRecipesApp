package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.base.BaseCoroutinesUseCase

class AddAllDataUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<ResultResponse, Long>() {
    override suspend fun execute(param: ResultResponse): UseCaseResult<Long> {
        return dataRepository.addAllPostsMenuToDb(param)
    }

}