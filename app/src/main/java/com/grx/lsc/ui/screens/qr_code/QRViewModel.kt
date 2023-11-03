package com.grx.lsc.ui.screens.qr_code

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.use_case.networks.UploadVehicleNoUseCase
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.ui.navigation.sharedViewModel
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val bottomNavigator: BottomNavigator,
    private val getTokenUseCase: GetTokenUseCase,
    private val uploadVehicleNoUseCase: UploadVehicleNoUseCase,
) : BaseViewModel<QRCodeContract.Event, QRCodeContract.State>(QRCodeContract.State()) {


    @Composable
    fun SetSharedData(navBackStackEntry: NavBackStackEntry) {
        val driverJobDetailsRes =
            navBackStackEntry
                .sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                .state.value.driverJobDetailsRes
        setState {
            copy(driverJobDetailsRes = driverJobDetailsRes)
        }
    }

    override fun event(event: QRCodeContract.Event) {
        event.apply {
            when (this) {
                is QRCodeContract.Event.UploadVehicleNumber -> {
                    uploadVehicleNumber()
                }

                is QRCodeContract.Event.ScanQRCode -> {
                    bottomNavigator.navController.popBackStack()
                    bottomNavigator.navController.navigate(AppRoute.BoggieTrailer.route)
                }

                is QRCodeContract.Event.OnBackPressed -> {
                    bottomNavigator.navController.popBackStack()
                }

                is QRCodeContract.Event.OnChangedQrCode -> {
                    setState {
                        copy(code = newCode)
                    }
                }
            }
        }
    }

    private fun uploadVehicleNumber() {
        viewModelScope.launch(Dispatchers.IO) {

            uploadVehicleNoUseCase(
                vehicleNumber = state.value.code.replace("Vehicle Number Is", ""),
                id = state.value.driverJobDetailsRes?.data?.id.toString(),
                token = "Bearer " + getTokenUseCase()
            )
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {

                            setState {
                                copy(
                                    isLoading = false,
                                    success = true
                                )
                            }
                            Handler(Looper.getMainLooper()).postDelayed({
                                bottomNavigator.navController.popBackStack()
                                bottomNavigator.navController.navigate(AppRoute.BoggieTrailer.route)
                            }, 1000)

                        }

                        is Resource.Error -> {
                            setState {
                                copy(
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Loading -> {
                            setState {
                                copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }

}