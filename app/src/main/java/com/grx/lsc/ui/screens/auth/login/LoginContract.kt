package com.grx.lsc.ui.screens.auth.login


class LoginContract {

    companion object{
        const val dialingCode = "+91"
    }

    sealed class Event {
        data class OnChangedMobileNumber(val newMobileNo: String) : Event()
        data class OnChangedMobileOTP(val newMobileOTP: String) : Event()
        data class OnChangedPassword(val newPassword: String) : Event()
        object SendOtp : Event()
        object Login : Event()
        object VerifyCode : Event()
        object OnBackPress : Event()
    }

    data class State(
        val mobileNo: String = "",
        val mobileOtp: String = "",
        val password: String = "",
        val mobileError: String? = null,
        val mobileOTPError: String? = null,
        val isLoading: Boolean = false,
    )
}
