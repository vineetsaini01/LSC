package com.grx.lsc.ui.screens.qr_code

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.use_case.networks.UploadVehicleNoUseCase
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
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


    fun setSharedData(driverJobDetailsRes: DriverJobDetailsRes) {
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
                    bottomNavigator.popBackStack()
                    bottomNavigator.navigate(AppRoute.BogieTrailer)
                }

                is QRCodeContract.Event.OnBackPressed -> {
                    bottomNavigator.popBackStack()
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
                                bottomNavigator.popBackStack()
                                bottomNavigator.navigate(AppRoute.BogieTrailer)
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