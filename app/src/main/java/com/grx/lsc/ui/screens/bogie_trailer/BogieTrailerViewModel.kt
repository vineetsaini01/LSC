package com.grx.lsc.ui.screens.bogie_trailer

import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BogieTrailerViewModel @Inject constructor(
    private val bottomNavigator: BottomNavigator,
) : BaseViewModel<BogieTrailerContract.Event, BogieTrailerContract.State>(BogieTrailerContract.State()) {



    fun setSharedData(driverJobDetailsRes: DriverJobDetailsRes) {
        setState {
            copy(driverJobDetailsRes = driverJobDetailsRes)
        }
    }


    override fun event(event: BogieTrailerContract.Event) {
        event.apply {
            when (this) {
                is BogieTrailerContract.Event.OnPressedChangeNo -> {
                    setState {
                        copy(showAlertChangeNo = true)
                    }
                }

                is BogieTrailerContract.Event.OnPressedYesConfirmBtn -> {
                    setState {
                        copy(showAlertTripBeenStarted = true)
                    }
                    viewModelScope.launch {
                        delay(2000)
                        setState {
                            copy(showAlertTripBeenStarted = false)
                        }
                        bottomNavigator.navigate(AppRoute.EnterDetails)
                    }
                }

                is BogieTrailerContract.Event.OnChangedTabIndex -> {

                    setState {
                        copy(tabIndex = newIndex)
                    }
                }

                is BogieTrailerContract.Event.OnChangedBoggieNumber -> {
                    setState {
                        copy(boggieNumber = newBoggieNumber)
                    }
                }

                is BogieTrailerContract.Event.OnChangedTrailerNumber -> {
                    setState {
                        copy(trailerNumber = newTrailerNumber)
                    }
                }

                is BogieTrailerContract.Event.OnDismissRequestAlert -> {

                    setState {
                        copy(showAlertChangeNo = false, showAlertTripBeenStarted = false)
                    }
                }

                is BogieTrailerContract.Event.OnClickBoggieNumberEnabled -> {
                    setState {
                        copy(hasBoggieNumberEnabled = !this.hasBoggieNumberEnabled)
                    }
                }

                is BogieTrailerContract.Event.OnClickTrailerNumberEnabled -> {

                    setState {
                        copy(hasTrailerNumberEnabled = !this.hasTrailerNumberEnabled)
                    }
                }
            }
        }
    }


}