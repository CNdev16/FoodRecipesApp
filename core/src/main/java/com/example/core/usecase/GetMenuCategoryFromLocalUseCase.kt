package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.usecase.base.BaseCoroutinesUseCase

class GetMenuCategoryFromLocalUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<Unit, List<MenuCategory>>() {
    override suspend fun execute(
        param: Unit,
        isInternetConnected: Boolean
    ): UseCaseResult<List<MenuCategory>> {
        return dataRepository.getMenuCategoryFromLocal()
    }
}