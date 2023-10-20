package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grx.lsc.ui.screens.login.LoginOtpScreen
import com.grx.lsc.ui.screens.login.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.Login.route
    ) {

        loginNavGraph(navController)
        loginOtpNavGraph(navController)
    }
}

fun NavGraphBuilder.loginNavGraph(navController: NavHostController) {
    composable(route = AppScreens.Login.route) {
        LoginScreen(navController = navController)
    }
}

fun NavGraphBuilder.loginOtpNavGraph(navController: NavHostController) {
    composable(route = AppScreens.LoginOtp.route) {
        LoginOtpScreen(navController=navController)
    }
}

