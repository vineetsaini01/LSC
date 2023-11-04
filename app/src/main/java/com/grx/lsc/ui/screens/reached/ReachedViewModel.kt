package com.grx.lsc.ui.screens.reached

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.use_case.networks.UpdateStatusUseCase
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
class ReachedViewModel @Inject constructor(
    private val bottomNavigator: BottomNavigator,
    private val updateStatusUseCase: UpdateStatusUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) :
    BaseViewModel<ReachedContract.Event, ReachedContract.State>(ReachedContract.State()) {

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

    override fun event(event: ReachedContract.Event) {
        event.apply {
            when (this) {

                ReachedContract.Event.OnDismissDelayAlert -> {
                    setState {
                        copy(showDelayAlert = false)
                    }
                }

                ReachedContract.Event.OnDismissReachedAlert -> {
                    setState {
                        copy(showReachedAlert = false)
                    }
                }

                ReachedContract.Event.OnPressedDelayBtn -> {
                    setState {
                        copy(showDelayAlert = true)
                    }

                }

                ReachedContract.Event.OnPressedReachedBtn -> {

                    setState {
                        copy(showReachedAlert = true)
                    }
                }

                is ReachedContract.Event.UpdateStatus -> {
                    updateStatus(status = status, reason = reason)
                }

                ReachedContract.Event.OnPressedSkipBtn -> {
                    setState {
                        copy(showReachedAlert = false, isWaitingLoading = true)
                    }
                }
            }
        }
    }


    private fun updateStatus(status: String, reason: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            updateStatusUseCase(
                token = "Bearer " + getTokenUseCase(),
               // id = "1",
                id = state.value.driverJobDetailsRes!!.data!!.id!!.toString(),
                status = status,
                reason = reason
            ).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState {
                            copy(isLoading = false)
                        }
                        if (resource.data?.message == "Status updated successfully") {
                            when(status){
                                "delay"->{
                                    setState {
                                        copy(showDelayAlert = false)
                                    }
                                }
                                "Container Grounded"->{
                                    setState {
                                        copy(showReachedAlert = false)
                                    }
                                }
                                "Trailer Cut"->{
                                    setState {
                                        copy(showReachedAlert = false)
                                    }
                                }
                                else->{

                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        setState {
                            copy(isLoading = false)
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