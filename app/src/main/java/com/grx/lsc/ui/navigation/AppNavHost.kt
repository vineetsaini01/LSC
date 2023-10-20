package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.ui.screens.login.LoginOtpScreen
import com.grx.lsc.ui.screens.login.LoginScreen
import com.grx.lsc.ui.screens.login.LoginViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>().apply {
        this.navController = navController
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.Login.route
    ) {
        loginNavGraph(loginViewModel)
        loginOtpNavGraph(loginViewModel)
        homeNavGraph(navController)
    }
}

fun NavGraphBuilder.loginNavGraph(loginViewModel: LoginViewModel) {
    composable(route = AppScreens.Login.route) {
        LoginScreen(viewModel = loginViewModel)
    }
}

fun NavGraphBuilder.loginOtpNavGraph(loginViewModel: LoginViewModel) {
    composable(route = AppScreens.LoginOtp.route) {
        LoginOtpScreen(viewModel = loginViewModel)
    }
}

fun NavGraphBuilder.homeNavGraph(navController:NavHostController) {
    composable(route = AppScreens.Home.route) {
        val viewModel=hiltViewModel<HomeViewModel>().apply {
            this.navController = navController
        }
        HomeScreen(viewModel = viewModel)
    }
}


