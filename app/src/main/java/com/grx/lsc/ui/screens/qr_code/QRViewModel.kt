package com.grx.lsc.ui.screens.qr_code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.repository.Repository
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
) : BaseViewModel<QREvent>() {

    var isLoading by mutableStateOf(false)
    override fun onEvent(event: QREvent) {
        event.apply {
            when (this) {
                QREvent.UploadVehicleNumber -> {
                    uploadVehicleNumber()
                }
            }
        }
    }

    private fun uploadVehicleNumber() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.uploadVehicleNumber(
                vehicleNumber = "",
                id = ""
            )
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            isLoading = false

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