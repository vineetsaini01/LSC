package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.screens.auth.login.LoginScreen
import com.grx.lsc.ui.screens.auth.login.LoginViewModel
import com.grx.lsc.ui.screens.landing.LandingScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreens.Landing.route
    ) {

        composable("landing") {
            val auth = navController.navigate("auth")
            val home = navController.navigate("home")
            LandingScreen(navController)

        }
        navigation(
            startDestination = "login",
            route = "auth_route"
        ) {

            composable("login") {
                val auth = navController.navigate("login_otp")
                val viewModel = it.sharedViewModel<LoginViewModel>(navController)
                LoginScreen(viewModel = viewModel)

            }
            composable("login_otp") {
                val viewModel = it.sharedViewModel<LoginViewModel>(navController)
                //LoginOtpScreen(viewModel = viewModel)
            }
        }

        navigation(
            startDestination = "home",
            route = "home_route"
        ) {


        }

    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}



