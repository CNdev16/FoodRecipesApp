package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.usecase.base.BaseCoroutinesUseCase

class DeleteMenuCategoryFromLocalUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<MenuCategory, Int>() {
    override suspend fun execute(
        param: MenuCategory,
        isInternetConnected: Boolean
    ): UseCaseResult<Int> {
        return dataRepository.deleteMenuCategoryFromLocal(param)
    }
}