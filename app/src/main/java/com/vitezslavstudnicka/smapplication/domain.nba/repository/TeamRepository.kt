package com.vitezslavstudnicka.smapplication.domain.nba.repository

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId

interface TeamRepository {
    suspend fun getTeam(id: TeamId): Result<Team>
}