package com.grx.lsc.ui.navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens(route = "Login")
    object LoginOtp : AppScreens(route = "LoginOtp")
    object Home : AppScreens(route = "Home")
    object BoggieTrailer : AppScreens(route = "BoggieTrailer")
    object EnterDetails : AppScreens(route = "EnterDetails")
    object Reached : AppScreens(route = "Reached")
    object Landing : AppScreens(route = "Landing")
}




