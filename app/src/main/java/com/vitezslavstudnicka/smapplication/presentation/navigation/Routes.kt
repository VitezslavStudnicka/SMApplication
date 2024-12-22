package com.vitezslavstudnicka.smapplication.presentation.navigation

object Routes {
    const val PLAYER_LIST = "players"
    const val PLAYER_DETAIL = "players/{playerId}"
    fun createPlayerDetailRoute(playerId: Long): String = "players/$playerId"
    
    const val TEAM_DETAIL = "teams/{teamId}"
    fun createTeamDetailRoute(teamId: Long): String = "teams/$teamId"
}
