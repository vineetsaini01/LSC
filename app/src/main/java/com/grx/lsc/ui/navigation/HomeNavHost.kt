package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerScreen
import com.grx.lsc.ui.screens.enter_details.EnterDetailsScreen
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.ui.screens.reached.ReachedScreen

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
        enterDetailsNavGraph(navController)
        reachedNavGraph(navController)
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

fun NavGraphBuilder.enterDetailsNavGraph(navController: NavHostController) {
    composable(route = AppScreens.EnterDetails.route) {
        EnterDetailsScreen(navController)
    }
}

fun NavGraphBuilder.reachedNavGraph(navController: NavHostController) {
    composable(route = AppScreens.Reached.route) {
        ReachedScreen(navController)
    }
}


