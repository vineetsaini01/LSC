package com.grx.lsc.ui.screens.qr_code

sealed class QREvent {
    object UploadVehicleNumber : QREvent()
    object ScanQRCode : QREvent()
    object OnBackPressed : QREvent()

}
