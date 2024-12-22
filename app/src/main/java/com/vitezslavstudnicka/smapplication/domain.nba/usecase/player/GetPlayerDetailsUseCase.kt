package com.vitezslavstudnicka.smapplication.domain.nba.usecase.player

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId
import com.vitezslavstudnicka.smapplication.domain.nba.repository.PlayerRepository
import javax.inject.Inject

class GetPlayerDetailsUseCase @Inject constructor(private val repository: PlayerRepository) {
    /**
     * Use case for fetching the details of a specific player.
     *
     * This use case is responsible for retrieving a player's details
     * from the underlying [PlayerRepository].
     *
     * @property repository The repository used to fetch player data.
     */
    suspend operator fun invoke(id: PlayerId): Result<Player> = repository.getPlayer(id)
}