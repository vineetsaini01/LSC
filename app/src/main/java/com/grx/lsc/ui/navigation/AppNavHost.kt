package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.screens.auth.login.LoginOtpScreen
import com.grx.lsc.ui.screens.auth.login.LoginScreen
import com.grx.lsc.ui.screens.auth.login.LoginViewModel
import com.grx.lsc.ui.screens.bottom_nav.BottomNavScreen
import com.grx.lsc.ui.screens.landing.LandingScreen
import com.grx.lsc.ui.screens.splash.SplashScreen
import com.grx.lsc.ui.screens.splash.SplashViewModel
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator,
) {

    NavHost(
        modifier = modifier,
        navController = appNavigator.navController,
        startDestination = AppRoute.Splash.route,
    ) {

        composable(AppRoute.Splash.route) {
            it.sharedViewModel<SplashViewModel>(appNavigator.navController)
            SplashScreen()
        }

        navigation(
            startDestination = AppRoute.Landing.route,
            route = AppRoute.AuthRoute.route
        ) {
            composable(AppRoute.Landing.route) {
                LandingScreen(appNavigator)
            }
            composable(AppRoute.Login.route) {
                val viewModel = it.sharedViewModel<LoginViewModel>(appNavigator.navController)
                LoginScreen(viewModel = viewModel)
            }
            composable(AppRoute.LoginOtp.route) {
                val viewModel = it.sharedViewModel<LoginViewModel>(appNavigator.navController)
                LoginOtpScreen(viewModel = viewModel)
            }
        }

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






