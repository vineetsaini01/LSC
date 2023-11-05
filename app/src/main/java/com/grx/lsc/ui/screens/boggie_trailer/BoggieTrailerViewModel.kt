package com.grx.lsc.ui.screens.boggie_trailer

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.core.base_view_model.BaseViewModelOld
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.ui.navigation.sharedViewModel
import com.grx.lsc.ui.screens.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoggieTrailerViewModel @Inject constructor(
    private val bottomNavigator: BottomNavigator,
) : BaseViewModel<BoggieTrailerContract.Event, BoggieTrailerContract.State>(BoggieTrailerContract.State()) {



    fun setSharedData(driverJobDetailsRes: DriverJobDetailsRes) {
        setState {
            copy(driverJobDetailsRes = driverJobDetailsRes)
        }
    }


    override fun event(event: BoggieTrailerContract.Event) {
        event.apply {
            when (this) {
                is BoggieTrailerContract.Event.OnPressedChangeNo -> {
                    setState {
                        copy(showAlertChangeNo = true)
                    }
                }

                is BoggieTrailerContract.Event.OnPressedYesConfirmBtn -> {
                    setState {
                        copy(showAlertTripBeenStarted = true)
                    }
                    viewModelScope.launch {
                        delay(2000)
                        setState {
                            copy(showAlertTripBeenStarted = false)
                        }
                        bottomNavigator.navController.navigate(AppRoute.EnterDetails.route)
                    }
                }

                is BoggieTrailerContract.Event.OnChangedTabIndex -> {

                    setState {
                        copy(tabIndex = newIndex)
                    }
                }

                is BoggieTrailerContract.Event.OnChangedBoggieNumber -> {
                    setState {
                        copy(boggieNumber = newBoggieNumber)
                    }
                }

                is BoggieTrailerContract.Event.OnChangedTrailerNumber -> {
                    setState {
                        copy(trailerNumber = newTrailerNumber)
                    }
                }

                is BoggieTrailerContract.Event.OnDismissRequestAlert -> {

                    setState {
                        copy(showAlertChangeNo = false, showAlertTripBeenStarted = false)
                    }
                }

                is BoggieTrailerContract.Event.OnClickBoggieNumberEnabled -> {
                    setState {
                        copy(hasBoggieNumberEnabled = !this.hasBoggieNumberEnabled)
                    }
                }

                is BoggieTrailerContract.Event.OnClickTrailerNumberEnabled -> {

                    setState {
                        copy(hasTrailerNumberEnabled = !this.hasTrailerNumberEnabled)
                    }
                }
            }
        }
    }


}