package com.grx.lsc.ui.navigation

sealed class AppRoute(val route: String) {
    object AuthRoute : AppRoute("auth_route")
    object BottomNavRoute : AppRoute("bottom_nav_route")
    object HomeRoute : AppRoute("home_route")
}

sealed class AppDestination(val route: String) {
    object Splash : AppRoute("splash")
    object Landing : AppRoute("landing")
    object Login : AppRoute("login")
    object LoginOtp : AppRoute("login_otp")
    object Home : AppRoute("home")
    object BottomNav : AppRoute("bottom_nav")
    object BoggieTrailer : AppRoute("boggie_trailer")
    object EnterDetails : AppRoute("enter_details")
    object Reached : AppRoute("reached")
    object QRCode : AppRoute("qr_code")
}

