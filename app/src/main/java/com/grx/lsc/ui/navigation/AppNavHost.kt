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

@Composable
fun AppNavHost(
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
        startDestination = AppScreens.Login.route
    ) {
        loginNavGraph(loginViewModel)
        loginOtpNavGraph(loginViewModel){
            startHomeActivity()
        }
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



