package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grx.lsc.ui.screens.auth.login.LoginOtpScreen
import com.grx.lsc.ui.screens.auth.login.LoginScreen
import com.grx.lsc.ui.screens.auth.login.LoginViewModel
import com.grx.lsc.ui.screens.landing.LandingScreen

@Composable
fun AuthNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startHomeActivity: () -> Unit,
) {

    val loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>().apply {
        this.navController = navController
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.Landing.route
    ) {
        landingNavGraph(navController)
        loginNavGraph(loginViewModel)
        loginOtpNavGraph(loginViewModel){
            startHomeActivity()
        }
    }
}

fun NavGraphBuilder.landingNavGraph(navController: NavHostController) {
    composable(route = AppScreens.Landing.route) {
        LandingScreen(navController)
    }
}

fun NavGraphBuilder.loginNavGraph(loginViewModel: LoginViewModel) {
    composable(route = AppScreens.Login.route) {
        LoginScreen(viewModel = loginViewModel)
    }
}

fun NavGraphBuilder.loginOtpNavGraph(loginViewModel: LoginViewModel, startHomeActivity:()-> Unit) {
    composable(route = AppScreens.LoginOtp.route) {
        LoginOtpScreen(loginViewModel){
            startHomeActivity()
        }
    }
}



