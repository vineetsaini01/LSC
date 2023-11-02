package com.grx.lsc.ui.screens.qr_code

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.repository.Repository
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
    private val repository: Repository,
    private val bottomNavigator: BottomNavigator,
    private val getTokenUseCase: GetTokenUseCase,
) : BaseViewModel<QREvent>() {

    var isLoading by mutableStateOf(false)
    var success by mutableStateOf(false)
    var code by mutableStateOf("")
    var driverJobDetailsRes: DriverJobDetailsRes? = null
    override fun onEvent(event: QREvent) {
        event.apply {
            when (this) {
                QREvent.UploadVehicleNumber -> {

                    uploadVehicleNumber()
                }

                QREvent.ScanQRCode -> {
                    bottomNavigator.navController.popBackStack()
                    bottomNavigator.navController.navigate(AppRoute.BoggieTrailer.route)
                }

                QREvent.OnBackPressed -> {
                    bottomNavigator.navController.popBackStack()
                }
            }
        }
    }

    private fun uploadVehicleNumber() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.uploadVehicleNumber(
                vehicleNumber = code.replace("Vehicle Number Is", ""),
                id = driverJobDetailsRes?.data?.id.toString(),
                token = "Bearer " + getTokenUseCase()
            )
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {

                            isLoading = false
                            success = true
                            Handler(Looper.getMainLooper()).postDelayed({
                                bottomNavigator.navController.popBackStack()
                                bottomNavigator.navController.navigate(AppRoute.BoggieTrailer.route)
                            }, 1000)

                        }

                        is Resource.Error -> {
                            isLoading = false
                        }

                        is Resource.Loading -> {
                            isLoading = true
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }

}