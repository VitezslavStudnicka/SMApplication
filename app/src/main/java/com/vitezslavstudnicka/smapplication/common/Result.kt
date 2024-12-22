package com.vitezslavstudnicka.smapplication.common

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: NetworkError) : Result<Nothing>()
}