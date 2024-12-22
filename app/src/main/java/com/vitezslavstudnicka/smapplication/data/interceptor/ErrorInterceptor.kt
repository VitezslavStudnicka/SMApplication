package com.vitezslavstudnicka.smapplication.data.interceptor

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.common.NetworkErrorHandler
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw NetworkError.NoInternet
        }

        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            val errorBody = response.body?.string()
            throw NetworkErrorHandler.handleError(response.code, errorBody)
        }

        return response
    }

    private fun isNetworkAvailable(): Boolean {
        return true
    }
}