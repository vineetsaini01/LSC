package com.grx.lsc.ui.screens.home


import com.grx.lsc.domain.models.DriverJobDetailsRes



class HomeContract{

    sealed class Event {
        data class OnPressedAcceptOrReject(val status: String) : Event()

        object OnPressedDocDownload : Event()
        object OnPressedQrCode : Event()
    }

    data class State(
        val driverJobDetailsRes: DriverJobDetailsRes?=null,
        val hasExpended: Boolean=false,
        val isLoading: Boolean=false,
    )

}