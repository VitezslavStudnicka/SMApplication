package com.vitezslavstudnicka.smapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NBAPlayersApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PLAYER_LIST
    ) {
        addPlayerListScreen(navController)
        addPlayerDetailScreen(navController)
        addTeamDetailScreen(navController)
    }
}