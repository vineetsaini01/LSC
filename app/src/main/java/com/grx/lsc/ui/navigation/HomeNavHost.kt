package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerScreen
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel

@Composable
fun HomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        homeNavGraph(navController)
        boggieTrailerNavGraph(navController)
    }
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    composable(route = AppScreens.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        viewModel.navController = navController
        HomeScreen(viewModel = viewModel)
    }
}

fun NavGraphBuilder.boggieTrailerNavGraph(navController: NavHostController) {
    composable(route = AppScreens.BoggieTrailer.route) {
        BoggieTrailerScreen(navController)
    }
}


