package com.vitezslavstudnicka.smapplication.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vitezslavstudnicka.smapplication.domain.nba.model.TeamId
import com.vitezslavstudnicka.smapplication.presentation.team.detail.TeamDetailScreen

fun NavGraphBuilder.addTeamDetailScreen(navController: NavController) {
    composable(
        route = Routes.TEAM_DETAIL,
        arguments = listOf(navArgument("teamId") { type = NavType.LongType })
    ) { backStackEntry ->
        val teamId = backStackEntry.arguments?.getLong("teamId") ?: return@composable
        TeamDetailScreen(
            teamId = TeamId(teamId),
            onBackClick = {
                navController.navigateUp()
            }
        )
    }
}
