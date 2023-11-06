package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.ui.navigation.nav_graph.homeNavGraph

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    bottomNavigator: BottomNavigator,
    driverJobDetailsRes: DriverJobDetailsRes,
    startDestination: String = if (driverJobDetailsRes.data?.status == "accaept") AppRoute.QRCode.route
    else AppRoute.Home.route,
) {


    NavHost(
        modifier = modifier,
        navController = bottomNavigator.getNavController()!!,
        startDestination = AppRoute.HomeRoute.route,
    ) {
        homeNavGraph(
            startDestination = startDestination,
            driverJobDetailsRes = driverJobDetailsRes,
            route = AppRoute.HomeRoute.route
        )

    }
}




