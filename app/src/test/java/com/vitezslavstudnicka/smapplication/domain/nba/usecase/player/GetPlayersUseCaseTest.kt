package com.vitezslavstudnicka.smapplication.domain.nba.usecase.player

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPlayersUseCaseTest {
    private lateinit var repository: PlayerRepository
    private lateinit var useCase: GetPlayersUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetPlayersUseCase(repository)
    }

    @Test
    fun `get players returns success with list of players and cursor`() = runTest {
        val players = listOf(mockk<Player>())
        val nextCursor = 70
        val expectedResult = Pair(players, nextCursor)
        coEvery { repository.getPlayers(any(), any()) } returns Result.Success(expectedResult)

        val result = useCase(cursor = null, perPage = 35)

        assertTrue(result is Result.Success)
        assertEquals(expectedResult, (result as Result.Success).data)
        assertEquals(players, result.data.first)
        assertEquals(nextCursor, result.data.second)
    }

    @Test
    fun `get players returns error when repository fails`() = runTest {
        val error = NetworkError.Unknown("Test error")
        coEvery { repository.getPlayers(any(), any()) } returns Result.Error(error)

        val result = useCase(cursor = null, perPage = 35)

        assertTrue(result is Result.Error)
        assertEquals(error, (result as Result.Error).error)
    }

}