package com.grx.lsc.ui.screens.auth.login


class LoginContract {
    sealed class Event {
        data class OnChangedMobileNumber(val newMobileNo: String) : Event()
        data class OnChangedMobileOTP(val newMobileOTP: String) : Event()
        object SendOtp : Event()
        object VerifyCode : Event()
        object OnBackPress : Event()
    }

    data class State(
        val mobileNo: String="",
        val mobileOtp: String="",
        val mobileError: String?=null,
        val mobileOTPError: String?=null,
        val isLoading: Boolean=false,
    )
}
