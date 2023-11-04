package com.grx.lsc.ui.screens.reached


import com.grx.lsc.domain.models.DriverJobDetailsRes


class ReachedContract {
    companion object {
        val delayListItems = listOf(
            "Container not received",
            "Traffic jam",
            "Vehicle breakdown",
            "Tire puncture",
            "Other"
        )
    }

    sealed class Event {

        object OnPressedDelayBtn : Event()
        object OnPressedReachedBtn : Event()
        object OnPressedSkipBtn : Event()

        data class UpdateStatus(val reason: String? = null, val status: String) : Event()
        object OnDismissDelayAlert : Event()
        object OnDismissReachedAlert : Event()


    }

    data class State(
        val driverJobDetailsRes: DriverJobDetailsRes? = null,
        val code: String = "",
        val showDelayAlert: Boolean = false,
        val showReachedAlert: Boolean = false,
        val isLoading: Boolean = false,
        val isWaitingLoading: Boolean = false,
    )
}
