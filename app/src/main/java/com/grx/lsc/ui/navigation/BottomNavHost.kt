package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerScreen
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerViewModel
import com.grx.lsc.ui.screens.enter_details.EnterDetailsScreen
import com.grx.lsc.ui.screens.enter_details.EnterDetailsViewModel
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.ui.screens.qr_code.QRScannerScreen
import com.grx.lsc.ui.screens.qr_code.QRViewModel
import com.grx.lsc.ui.screens.splash.SplashViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    bottomNavigator: BottomNavigator,
) {


    NavHost(
        modifier = modifier,
        navController = bottomNavigator.navController,
        startDestination = AppRoute.HomeRoute.route,
    ) {
        navigation(
            startDestination = AppRoute.Home.route,
            route = AppRoute.HomeRoute.route
        ) {
            composable(AppRoute.Home.route) {
                val viewModel = it.sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                HomeScreen(viewModel)
            }

            composable(AppRoute.QRCode.route) {
                val viewModel = it.sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                QRScannerScreen(hiltViewModel<QRViewModel>().apply {
                    this.driverJobDetailsRes = viewModel.driverJobDetailsRes
                })
            }

            composable(AppRoute.BoggieTrailer.route) {

                val viewModel = it.sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                BoggieTrailerScreen(hiltViewModel<BoggieTrailerViewModel>().apply {
                    this.driverJobDetailsRes = viewModel.driverJobDetailsRes
                })
            }

            composable(AppRoute.EnterDetails.route) {

                val viewModel = it.sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                EnterDetailsScreen(hiltViewModel<EnterDetailsViewModel>().apply {
                    this.driverJobDetailsRes = viewModel.driverJobDetailsRes
                })
            }
        }

    }
}




