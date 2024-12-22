package com.vitezslavstudnicka.smapplication.domain.nba.usecase.team

import com.vitezslavstudnicka.smapplication.common.Result
import com.vitezslavstudnicka.smapplication.domain.nba.model.Team
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId
import com.vitezslavstudnicka.smapplication.domain.nba.repository.TeamRepository
import javax.inject.Inject

class GetTeamDetailsUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    /**
     * Use case for fetching the details of a specific team.
     *
     * This use case handles the retrieval of team details
     * from the underlying [TeamRepository].
     *
     * @property repository The repository used to fetch team data.
     */
    suspend operator fun invoke(id: TeamId): Result<Team> = repository.getTeam(id)
}