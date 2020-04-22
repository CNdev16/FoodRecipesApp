package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetAllDataCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Unit, List<ResultResponse>>() {
    override suspend fun execute(param: Unit): UseCaseResult<List<ResultResponse>> {
        return dataRepository.getAllData()
    }
}