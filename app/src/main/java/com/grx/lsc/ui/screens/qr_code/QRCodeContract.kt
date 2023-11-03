package com.grx.lsc.ui.screens.qr_code


import com.grx.lsc.domain.models.DriverJobDetailsRes


class QRCodeContract {
    sealed class Event {
        object UploadVehicleNumber : Event()

        data class OnChangedQrCode(val newCode: String) : Event()
        object ScanQRCode : Event()
        object OnBackPressed : Event()
    }

    data class State(
        val driverJobDetailsRes: DriverJobDetailsRes? = null,
        val code: String = "",
        val success: Boolean = false,
        val isLoading: Boolean = false,
    )
}
