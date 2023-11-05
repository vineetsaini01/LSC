package com.grx.lsc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerScreen
import com.grx.lsc.ui.screens.boggie_trailer.BoggieTrailerViewModel
import com.grx.lsc.ui.screens.enter_details.EnterDetailsScreen
import com.grx.lsc.ui.screens.enter_details.EnterDetailsViewModel
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.ui.screens.qr_code.QRScannerScreen
import com.grx.lsc.ui.screens.qr_code.QRViewModel
import com.grx.lsc.ui.screens.reached.ReachedScreen
import com.grx.lsc.ui.screens.reached.ReachedViewModel

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    bottomNavigator: BottomNavigator,
    driverJobDetailsRes: DriverJobDetailsRes,
    startDestination: String = if (driverJobDetailsRes.data?.status == "accept") AppRoute.QRCode.route
    else AppRoute.Home.route,
) {


    NavHost(
        modifier = modifier,
        navController = bottomNavigator.navController,
        startDestination = AppRoute.HomeRoute.route,
    ) {
        navigation(
            startDestination = startDestination,
            route = AppRoute.HomeRoute.route
        ) {
            composable(AppRoute.Home.route) {
                val viewModel = hiltViewModel<HomeViewModel>().apply {
                    setSharedData(driverJobDetailsRes = driverJobDetailsRes)
                }
                HomeScreen(viewModel)
            }

            composable(AppRoute.QRCode.route) {

                val viewModel = hiltViewModel<QRViewModel>().apply {
                    setSharedData(driverJobDetailsRes = driverJobDetailsRes)
                }
                QRScannerScreen(
                    state = viewModel.state.value,
                    event = viewModel::event
                )
            }

            composable(AppRoute.BoggieTrailer.route) {

                val viewModel = hiltViewModel<BoggieTrailerViewModel>().apply {
                    setSharedData(driverJobDetailsRes = driverJobDetailsRes)
                }
                BoggieTrailerScreen(
                    state = viewModel.state.value,
                    event = viewModel::event
                )
            }

            composable(AppRoute.EnterDetails.route) {
                val viewModel = hiltViewModel<EnterDetailsViewModel>().apply {
                    setSharedData(driverJobDetailsRes)
                    CameraLauncher()
                    DocsLauncher()
                }

                EnterDetailsScreen(
                    state = viewModel.state.value,
                    event = viewModel::event
                )
            }
            composable(AppRoute.Reached.route) {
                val viewModel = hiltViewModel<ReachedViewModel>().apply {
                    setSharedData(driverJobDetailsRes = driverJobDetailsRes)
                }
                ReachedScreen(
                    state = viewModel.state.value,
                    event = viewModel::event
                )
            }
        }

    }
}




