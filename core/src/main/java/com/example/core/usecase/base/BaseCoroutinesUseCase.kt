package com.example.core.usecase.base

import com.example.core.UseCaseResult

abstract class BaseCoroutinesUseCase<in I: Any, out O : Any>{
    abstract suspend fun execute(param : I):UseCaseResult<O>
}
