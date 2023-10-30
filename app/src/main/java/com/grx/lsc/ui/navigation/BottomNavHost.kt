package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    bottomNavController: NavHostController,
    appNavController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = bottomNavController,
        startDestination = AppRoute.HomeRoute.route,
    ) {
        navigation(
            startDestination = AppDestination.Home.route,
            route = AppRoute.HomeRoute.route
        ) {
            var id = ""
            composable(AppDestination.Home.route) {
                val viewModel = hiltViewModel<HomeViewModel>().apply {
                    this.appNavController = appNavController
                    this.homeNavController = bottomNavController
                }
                HomeScreen(viewModel)
            }
        }
    }
}




