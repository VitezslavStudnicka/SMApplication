package com.vitezslavstudnicka.smapplication.common

object NetworkErrorHandler {
    fun handleError(code: Int, errorMessage: String? = null): NetworkError {
        return when (code) {
            401 -> NetworkError.Unauthorized
            400 -> NetworkError.BadRequest(errorMessage)
            404 -> NetworkError.NotFound(errorMessage)
            429 -> NetworkError.RateLimited
            500 -> NetworkError.ServerError(errorMessage)
            503 -> NetworkError.ServerError("Service Unavailable")
            else -> NetworkError.Unknown(errorMessage)
        }
    }
}