package com.vitezslavstudnicka.smapplication.presentation.player.detail

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.domain.nba.model.Player

data class PlayerDetailState(
    val player: Player? = null,
    val isLoading: Boolean = false,
    val error: NetworkError? = null
)