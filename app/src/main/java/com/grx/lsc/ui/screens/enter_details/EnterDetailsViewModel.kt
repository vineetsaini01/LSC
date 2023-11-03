package com.grx.lsc.ui.screens.enter_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.core.base_view_model.BaseViewModelOld
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.domain.use_case.networks.DriverJobStoreUseCase
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
class EnterDetailsViewModel @Inject constructor(
    private val driverJobStoreUseCase: DriverJobStoreUseCase,
    private val bottomNavigator: BottomNavigator
) :
    BaseViewModel<EnterDetailsContract.Event, EnterDetailsContract.State>(EnterDetailsContract.State()) {

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


    override fun event(event: EnterDetailsContract.Event) {
        event.apply {
            when (this) {
                is EnterDetailsContract.Event.OnChangedContainerNo -> {
                    setState {
                        copy(containerNo = newContainerNo)
                    }
                }

                is EnterDetailsContract.Event.OnChangedSealNo -> {
                    setState {
                        copy(sealNo = newSealNo)
                    }
                }

                EnterDetailsContract.Event.OnPressedDoneBtn -> TODO()
                EnterDetailsContract.Event.OnPressedUploadDoc -> TODO()
                EnterDetailsContract.Event.OnPressedUploadImage -> TODO()
            }
        }
    }

    private fun driverJobStore() {
        viewModelScope.launch(Dispatchers.IO) {

            driverJobStoreUseCase(
                id = state.value.driverJobDetailsRes?.data?.id.toString(),
                containerNo = state.value.containerNo,
                imagesVideos = "",
                sealNo = state.value.sealNo
            )
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            setState {
                                copy(isLoading = true)
                            }
                        }

                        is Resource.Error -> {
                            setState {
                                copy(isLoading = true)
                            }
                        }

                        is Resource.Loading -> {
                            setState {
                                copy(isLoading = true)
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }


}