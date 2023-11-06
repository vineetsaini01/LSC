package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.navigation.nav_graph.authNavGraph
import com.grx.lsc.ui.screens.bottom_nav.BottomNavScreen
import com.grx.lsc.ui.screens.splash.SplashScreen
import com.grx.lsc.ui.screens.splash.SplashViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator,
    navController: NavHostController = appNavigator.getNavController()!!,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.Splash.route,
    ) {

        composable(AppRoute.Splash.route) {
            it.sharedViewModel<SplashViewModel>(navController)
            SplashScreen()
        }

        authNavGraph(navController)

        navigation(
            startDestination = AppRoute.BottomNav.route,
            route = AppRoute.BottomNavRoute.route
        ) {
            composable(AppRoute.BottomNav.route) {
                BottomNavScreen()
            }
        }
    }
}






