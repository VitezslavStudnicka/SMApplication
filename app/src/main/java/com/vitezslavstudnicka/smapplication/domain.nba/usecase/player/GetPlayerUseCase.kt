package com.vitezslavstudnicka.smapplication.domain.nba.usecase.player

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(private val repository: PlayerRepository) {
    /**
     * Fetches a list of players, optionally paginated.
     *
     * @param cursor The cursor for pagination. Null to fetch the first page.
     * @param perPage Number of players to fetch per page. Defaults to [DEFAULT_PER_PAGE].
     * @return A [Result] containing a pair of the players list and the next cursor, or an error.
     */
    suspend operator fun invoke(
        cursor: String? = null,
        perPage: Int = DEFAULT_PAGE_SIZE
    ): Result<Pair<List<Player>, Int?>> = repository.getPlayers(cursor, perPage)

    companion object {
        const val DEFAULT_PAGE_SIZE = 35
    }
}