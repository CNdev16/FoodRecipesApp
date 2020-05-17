package com.example.core

sealed class UseCaseResult<out T : Any> {
    data class Success<out T : Any> constructor(
        val responseMessage: String,
        val data: T
    ) : UseCaseResult<T>()

    data class Error constructor(
        val responseMessage: String
    ) : UseCaseResult<Nothing>()
}