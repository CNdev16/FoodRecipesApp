package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.data.SubCate
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetSubCategoriesOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Int, List<SubCate>>() {
    override suspend fun execute(param: Int): UseCaseResult<List<SubCate>> {
        return dataRepository.getSubCategoriesOnly(param)
    }

}