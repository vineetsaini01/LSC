package com.grx.lsc.ui.screens.bogie_trailer

import com.grx.lsc.domain.models.DriverJobDetailsRes


class BogieTrailerContract {

    companion object{
        val tabLabelList = listOf("Boggie No", "Trailer NO")
    }

    sealed class Event {
        object OnPressedYesConfirmBtn : Event()

        object OnPressedChangeNo : Event()
        object OnDismissRequestAlert : Event()
        object OnClickBoggieNumberEnabled : Event()
        object OnClickTrailerNumberEnabled : Event()

        data class OnChangedTabIndex(val newIndex: Int) : Event()
        data class OnChangedBoggieNumber(val newBoggieNumber: String) : Event()
        data class OnChangedTrailerNumber(val newTrailerNumber: String) : Event()
    }

    data class State(
        val showAlertChangeNo: Boolean = false,
        val showAlertTripBeenStarted: Boolean = false,
        val boggieNumber: String = "",
        val trailerNumber: String = "",
        val hasBoggieNumberEnabled: Boolean = true,
        val hasTrailerNumberEnabled: Boolean = false,
        val driverJobDetailsRes: DriverJobDetailsRes? = null,
        val tabIndex: Int = 0,
    )
}