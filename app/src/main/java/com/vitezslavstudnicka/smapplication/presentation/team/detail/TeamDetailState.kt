package com.vitezslavstudnicka.smapplication.presentation.team.detail

import com.vitezslavstudnicka.smapplication.common.NetworkError
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team

data class TeamDetailState(
    val team: Team? = null,
    val isLoading: Boolean = false,
    val error: NetworkError? = null
)