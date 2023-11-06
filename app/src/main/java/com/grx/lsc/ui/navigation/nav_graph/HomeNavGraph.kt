package com.grx.lsc.ui.navigation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.screens.bogie_trailer.BogieTrailerScreen
import com.grx.lsc.ui.screens.bogie_trailer.BogieTrailerViewModel
import com.grx.lsc.ui.screens.enter_details.EnterDetailsScreen
import com.grx.lsc.ui.screens.enter_details.EnterDetailsViewModel
import com.grx.lsc.ui.screens.home.HomeScreen
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.ui.screens.qr_code.QRScannerScreen
import com.grx.lsc.ui.screens.qr_code.QRViewModel
import com.grx.lsc.ui.screens.reached.ReachedScreen
import com.grx.lsc.ui.screens.reached.ReachedViewModel

fun NavGraphBuilder.homeNavGraph(
    startDestination: String,
    driverJobDetailsRes: DriverJobDetailsRes,
    route: String,
) {

    navigation(
        startDestination = startDestination,
        route = route
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

        composable(AppRoute.BogieTrailer.route) {

            val viewModel = hiltViewModel<BogieTrailerViewModel>().apply {
                setSharedData(driverJobDetailsRes = driverJobDetailsRes)
            }
            BogieTrailerScreen(
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