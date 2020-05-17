package com.example.core.usecase

import com.example.core.DataRepository
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.base.BaseCoroutinesUseCase

class DeleteAllDataFromDbUseCase(private val dataRepository: DataRepository) :
    BaseCoroutinesUseCase<CountryCategory, Int>() {
    override suspend fun execute(
        param: CountryCategory,
        isInternetConnected: Boolean
    ): UseCaseResult<Int> {
        return dataRepository.deleteCountryCategoryFromDb(param)
    }
}