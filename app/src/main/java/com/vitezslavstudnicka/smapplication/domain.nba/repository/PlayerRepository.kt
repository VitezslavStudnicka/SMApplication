package com.vitezslavstudnicka.smapplication.domain.nba.repository

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId

interface PlayerRepository {
    /**
     * Fetches a list of players, optionally paginated.
     *
     * @param cursor Pagination cursor for fetching subsequent pages.
     * @param perPage Number of players to fetch in one call.
     * @return A [Result] containing the list of players and the next cursor, or an error.
     */
    suspend fun getPlayers(
        cursor: String? = null,
        perPage: Int = DEFAULT_PAGE_SIZE
    ): Result<Pair<List<Player>, Int?>>

    /**
     * Fetches details of a specific player.
     *
     * @param id The unique ID of the player to fetch.
     * @return A [Result] containing the player's details, or an error.
     */
    suspend fun getPlayer(id: PlayerId): Result<Player>

    companion object {
        const val DEFAULT_PAGE_SIZE = 35
    }
}