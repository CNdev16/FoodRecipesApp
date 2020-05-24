package com.example.core

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

fun ResponseBody.handleErrorMessage(): String {
    var jsonObject: JSONObject? = null
    jsonObject = JSONObject(this.string())
    return jsonObject.getString("message")
}

fun Throwable.handleThrowable(): UseCaseResult.Error {
    return when (this) {
        is HttpException -> {
            UseCaseResult.Error(responseMessage = arrayListOf("${this.code()}", this.message()))
        }
        else -> {
            UseCaseResult.Error(
                responseMessage = arrayListOf(
                    "Error",
                    this.message ?: "Unknown error."
                )
            )
        }
    }
}