package com.grx.lsc.ui.screens.auth.login

sealed class LoginEvent {
    data class OnChangedMobileNumber(val newMobileNo: String) : LoginEvent()
    data class OnChangedMobileOTP(val newMobileOTP: String) : LoginEvent()
    object SendOtp : LoginEvent()
    object VerifyCode : LoginEvent()
    object OnBackPress : LoginEvent()
}