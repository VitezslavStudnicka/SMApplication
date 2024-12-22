package com.vitezslavstudnicka.smapplication.di

import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId
import com.vitezslavstudnicka.smapplication.presentation.player.list.PlayerListState
import com.vitezslavstudnicka.smapplication.presentation.player.list.PlayerListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [ViewModelModule::class]
)
object TestViewModelModule {

    @Provides
    fun provideMockViewModel(): PlayerListViewModel {
        return mockk(relaxed = true) {
            coEvery { state } returns MutableStateFlow(
                PlayerListState(
                    players = listOf(
                        Player(
                            id = PlayerId(1),
                            firstName = "Stephen",
                            lastName = "Curry",
                            position = "G",
                            height = "6-3",
                            weight = "185",
                            jerseyNumber = "30",
                            college = "Davidson",
                            country = "USA",
                            draftYear = 2009,
                            draftRound = 1,
                            draftNumber = 7,
                            team = Team(
                                id = TeamId(1),
                                conference = "West",
                                division = "Pacific",
                                city = "Golden State",
                                name = "Warriors",
                                fullName = "Golden State Warriors",
                                abbreviation = "GSW"
                            )
                        )
                    ),
                    isLoading = false
                )
            )
        }
    }
}
