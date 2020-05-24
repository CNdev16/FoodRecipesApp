package com.example.core

sealed class UseCaseResult<out T : Any> {
    data class Success<out T : Any> @JvmOverloads constructor(
        val responseMessage: String,
        val data: T
    ) : UseCaseResult<T>()

    data class Error @JvmOverloads constructor(
        val responseMessage: ArrayList<String>
    ) : UseCaseResult<Nothing>()
}