package com.vitezslavstudnicka.smapplication.presentation.player.list

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.usecase.player.GetPlayersUseCase
import com.vitezslavstudnicka.smapplication.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlayerListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetPlayersUseCase
    private lateinit var viewModel: PlayerListViewModel

    @Before
    fun setup() {
        useCase = mockk()
        viewModel = PlayerListViewModel(useCase)
    }

    @Test
    fun `loading players updates state correctly`() = runTest {
        val players = listOf(mockk<Player>())
        val nextCursor = 70
        val dataPair = Pair(players, nextCursor)
        coEvery { useCase(any(), any()) } returns Result.Success(dataPair)

        viewModel.loadPlayers()

        assertEquals(players, viewModel.state.value.players)
        assertFalse(viewModel.state.value.isLoading)
        assertNull(viewModel.state.value.error)
    }
}