package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.MenuCategory
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetSubCategoriesOnlyUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Int, List<MenuCategory>>() {
    override suspend fun execute(
        param: Int,
        isInternetConnected: Boolean
    ): UseCaseResult<List<MenuCategory>> {
        return dataRepository.getSubCategoriesOnly(param, isInternetConnected)
    }

}