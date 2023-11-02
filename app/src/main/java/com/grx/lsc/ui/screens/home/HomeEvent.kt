package com.grx.lsc.ui.screens.home

sealed class HomeEvent {
    data class OnPressedAcceptOrReject(val status: String) : HomeEvent()

    object OnPressedDocDownload : HomeEvent()
    object OnPressedQrCode : HomeEvent()
}
