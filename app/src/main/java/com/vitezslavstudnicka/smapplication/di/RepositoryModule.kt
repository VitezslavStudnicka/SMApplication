package com.vitezslavstudnicka.smapplication.di

import com.vitezslavstudnicka.smapplication.BuildConfig
import com.vitezslavstudnicka.smapplication.data.remote.RetrofitClient
import com.vitezslavstudnicka.smapplication.data.remote.api.NbaApiService
import com.vitezslavstudnicka.smapplication.data.repository.PlayerRepositoryImpl
import com.vitezslavstudnicka.smapplication.data.repository.TeamRepositoryImpl
import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import com.vitezslavstudnicka.smapplication.domain.nba.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Using SingletonComponent for repositories
object RepositoryModule {
    @Provides
    @Singleton
    fun providePlayerRepository(
        api: NbaApiService,
        @Named("api_key") apiKey: String
    ): PlayerRepository {
        return PlayerRepositoryImpl(api, apiKey)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(
        api: NbaApiService,
        @Named("api_key") apiKey: String
    ): TeamRepository {
        return TeamRepositoryImpl(api, apiKey)
    }

    @Provides
    @Singleton
    fun provideNbaApiService(): NbaApiService {
        return RetrofitClient.nbaApi
    }

    @Provides
    @Named("api_key")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }
}