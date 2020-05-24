package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.usecase.base.BaseCoroutinesUseCase

class InsertMenuCategoryToLocalUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<MenuCategory, Long>() {
    override suspend fun execute(
        param: MenuCategory,
        isInternetConnected: Boolean
    ): UseCaseResult<Long> {
        return dataRepository.insertMenuCategoryToLocal(param)
    }

}