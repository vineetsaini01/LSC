package com.grx.lsc.ui.navigation

sealed class AppRoute(val route: String) {

    // APP ROUTE
    object AuthRoute : AppRoute("auth_route")
    object BottomNavRoute : AppRoute("bottom_nav_route")
    object HomeRoute : AppRoute("home_route")

    // APP DESTINATION
    object Splash : AppRoute("splash")
    object Landing : AppRoute("landing")
    object Login : AppRoute("login")
    object LoginOtp : AppRoute("login_otp")
    object Home : AppRoute("home")
    object BottomNav : AppRoute("bottom_nav")
    object BogieTrailer : AppRoute("bogie_trailer")
    object EnterDetails : AppRoute("enter_details")
    object Reached : AppRoute("reached")
    object QRCode : AppRoute("qr_code")
}



