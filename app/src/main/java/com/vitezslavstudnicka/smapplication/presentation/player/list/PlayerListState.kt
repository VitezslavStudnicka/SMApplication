package com.vitezslavstudnicka.smapplication.presentation.player.list

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player

data class PlayerListState(
    val players: List<Player> = emptyList(),
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val nextCursor: String? = null
)