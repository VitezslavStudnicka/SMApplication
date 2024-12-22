package com.vitezslavstudnicka.smapplication.common

sealed class NetworkError : Exception() {
    data object Unauthorized : NetworkError()
    data object RateLimited : NetworkError()
    data object NoInternet : NetworkError()
    data class ServerError(override val message: String?) : NetworkError()
    data class BadRequest(override val message: String?) : NetworkError()
    data class NotFound(override val message: String?) : NetworkError()
    data class Unknown(override val message: String?) : NetworkError()
}