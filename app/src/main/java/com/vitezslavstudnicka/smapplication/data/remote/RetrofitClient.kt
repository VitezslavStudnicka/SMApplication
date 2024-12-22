package com.vitezslavstudnicka.smapplication.data.remote

import com.vitezslavstudnicka.smapplication.BuildConfig
import com.vitezslavstudnicka.smapplication.data.interceptor.ErrorInterceptor
import com.vitezslavstudnicka.smapplication.data.remote.api.NbaApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ErrorInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nbaApi: NbaApiService = retrofit.create(NbaApiService::class.java)
}