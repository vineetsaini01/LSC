package com.grx.lsc.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.sharedViewModel
import com.grx.lsc.ui.screens.auth.login.LoginOtpScreen
import com.grx.lsc.ui.screens.auth.login.LoginScreen
import com.grx.lsc.ui.screens.auth.login.LoginViewModel
import com.grx.lsc.ui.screens.landing.LandingScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AppRoute.Landing.route,
        route = AppRoute.AuthRoute.route
    ) {
        composable(AppRoute.Landing.route) {
            LandingScreen(navController)
        }
        composable(AppRoute.Login.route) {
            val viewModel = it.sharedViewModel<LoginViewModel>(navController)
            LoginScreen(
                state = viewModel.state.value,
                onEvent = viewModel::event
            )
        }
        composable(AppRoute.LoginOtp.route) {
            val viewModel = it.sharedViewModel<LoginViewModel>(navController)
            LoginOtpScreen(
                state = viewModel.state.value,
                event = viewModel::event
            )
        }
    }

}