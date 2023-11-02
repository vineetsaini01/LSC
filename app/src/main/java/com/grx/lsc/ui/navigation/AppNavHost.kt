package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.screens.auth.login.LoginOtpScreen
import com.grx.lsc.ui.screens.auth.login.LoginScreen
import com.grx.lsc.ui.screens.auth.login.LoginViewModel
import com.grx.lsc.ui.screens.bottom_nav.BottomNavScreen
import com.grx.lsc.ui.screens.landing.LandingScreen
import com.grx.lsc.ui.screens.qr_code.QRScannerScreen
import com.grx.lsc.ui.screens.splash.SplashScreen
import com.grx.lsc.ui.screens.splash.SplashViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appNavController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = appNavController,
        startDestination = AppDestination.Splash.route,
    ) {

        composable(AppDestination.Splash.route) {
            it.sharedViewModel<SplashViewModel>(appNavController).apply {
                this.appNavController = appNavController
            }
            SplashScreen()
        }

        navigation(
            startDestination = AppDestination.Landing.route,
            route = AppRoute.AuthRoute.route
        ) {
            composable(AppDestination.Landing.route) {
                LandingScreen(appNavController)
            }
            composable(AppDestination.Login.route) {
                val viewModel = it.sharedViewModel<LoginViewModel>(appNavController)
                viewModel.navController = appNavController
                LoginScreen(viewModel = viewModel)
            }
            composable(AppDestination.LoginOtp.route) {
                val viewModel = it.sharedViewModel<LoginViewModel>(appNavController)
                LoginOtpScreen(viewModel = viewModel)
            }
        }

        navigation(
            startDestination = AppDestination.BottomNav.route,
            route = AppRoute.BottomNavRoute.route
        ) {
            composable(AppDestination.BottomNav.route) {
                BottomNavScreen(appNavController = appNavController)
            }

        }
    }
}






