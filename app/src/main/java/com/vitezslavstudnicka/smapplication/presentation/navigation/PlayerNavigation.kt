package com.vitezslavstudnicka.smapplication.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vitezslavstudnicka.smapplication.domain.nba.model.PlayerId
import com.vitezslavstudnicka.smapplication.presentation.player.detail.PlayerDetailScreen
import com.vitezslavstudnicka.smapplication.presentation.player.list.PlayerListScreen

fun NavGraphBuilder.addPlayerListScreen(navController: NavController) {
    composable(Routes.PLAYER_LIST) {
        PlayerListScreen(
            onPlayerClick = { playerId ->
                navController.navigate(Routes.createPlayerDetailRoute(playerId))
            }
        )
    }
}

fun NavGraphBuilder.addPlayerDetailScreen(navController: NavController) {
    composable(
        route = Routes.PLAYER_DETAIL,
        arguments = listOf(navArgument("playerId") { type = androidx.navigation.NavType.LongType })
    ) { backStackEntry ->
        val playerId = backStackEntry.arguments?.getLong("playerId") ?: return@composable
        PlayerDetailScreen(
            playerId = PlayerId(playerId),
            onTeamClick = { teamId ->
                navController.navigate(Routes.createTeamDetailRoute(teamId))
            },
            onBackClick = {
                navController.navigateUp()
            }
        )
    }
}
