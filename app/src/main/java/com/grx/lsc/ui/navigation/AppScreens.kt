package com.grx.lsc.ui.navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens(route = "Login")
    object LoginOtp : AppScreens(route = "LoginOtp")
    object Home : AppScreens(route = "Home")
}




