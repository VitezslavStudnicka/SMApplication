package com.vitezslavstudnicka.smapplication.di

import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import com.vitezslavstudnicka.smapplication.domain.nba.repository.TeamRepository
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.player.GetPlayerDetailsUseCase
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.player.GetPlayersUseCase
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.team.GetTeamDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideGetPlayersUseCase(repository: PlayerRepository): GetPlayersUseCase {
        return GetPlayersUseCase(repository)
    }

    @Provides
    fun provideGetPlayerDetailsUseCase(repository: PlayerRepository): GetPlayerDetailsUseCase {
        return GetPlayerDetailsUseCase(repository)
    }

    @Provides
    fun provideGetTeamDetailsUseCase(repository: TeamRepository): GetTeamDetailsUseCase {
        return GetTeamDetailsUseCase(repository)
    }
}