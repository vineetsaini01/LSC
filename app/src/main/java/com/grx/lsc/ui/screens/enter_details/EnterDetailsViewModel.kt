package com.grx.lsc.ui.screens.enter_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterDetailsViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<EnterDetailsEvent>() {

    var isLoading by mutableStateOf(false)

    var containerNo by mutableStateOf("")
    var sealNo by mutableStateOf("")
    var driverJobDetailsRes: DriverJobDetailsRes? = null


    override fun onEvent(event: EnterDetailsEvent) {
        event.apply {
            when (this) {
                EnterDetailsEvent.OnPressedDone -> {
                    driverJobStore()
                }
            }
        }
    }

    private fun driverJobStore() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.driverJobStore(
                id = "",
                containerNo = containerNo,
                imagesVideos = "",
                sealNo = sealNo
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