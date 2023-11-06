package com.grx.lsc.ui.screens.auth.login

import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.use_case.networks.SendVerificationCodeUseCase
import com.grx.lsc.domain.use_case.networks.VerifyCodeUseCase
import com.grx.lsc.domain.use_case.shared_pref.SaveTokenUseCase
import com.grx.lsc.domain.use_case.validation.ValidateOtp
import com.grx.lsc.domain.use_case.validation.ValidatePhoneNumber
import com.grx.lsc.ui.navigation.AppNavigator
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val validateOtp: ValidateOtp,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val appNavigator: AppNavigator,
    private val sendVerificationCodeUseCase: SendVerificationCodeUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : BaseViewModel<LoginContract.Event, LoginContract.State>(LoginContract.State()) {


    override fun event(event: LoginContract.Event) {

        event.apply {
            when (this) {
                is LoginContract.Event.OnChangedMobileNumber -> {
                    setState {
                        copy(mobileNo = newMobileNo, mobileError = null)
                    }
                }

                is LoginContract.Event.OnChangedMobileOTP -> {
                    setState {
                        copy(mobileOtp = newMobileOTP, mobileOTPError = null)
                    }
                }

                is LoginContract.Event.SendOtp -> {
                    val phoneNumberResult = validatePhoneNumber.invoke(state.value.mobileNo)
                    if (!phoneNumberResult.success) {
                        setState {
                            copy(mobileError = phoneNumberResult.message)
                        }
                        return
                    }
                    sendVerificationCode()
                }

                is LoginContract.Event.VerifyCode -> {
                    val phoneNumberResult = validateOtp(state.value.mobileOtp)
                    if (!phoneNumberResult.success) {
                        setState {
                            copy(mobileOTPError = phoneNumberResult.message)
                        }
                        return
                    }
                    verifyCode()
                }

                is LoginContract.Event.OnBackPress -> {
                    appNavigator.popBackStack()
                }
            }
        }
    }


    private fun sendVerificationCode() {
        viewModelScope.launch(Dispatchers.IO) {

            sendVerificationCodeUseCase(
                mobile = LoginContract.dialingCode + state.value.mobileNo
            ).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data?.message == "Verification code sent") appNavigator.navigate(
                            AppRoute.LoginOtp
                        )
                        else setState {
                            copy(mobileError = resource.data?.message)
                        }
                        setState {
                            copy(isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        setState {
                            copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        setState {
                            copy(isLoading = true)
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    private fun verifyCode() {
        viewModelScope.launch(Dispatchers.IO) {

            verifyCodeUseCase(
                verificationCode = state.value.mobileOtp,
                mobile = LoginContract.dialingCode + state.value.mobileNo
            ).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data?.message == "Login successful") {
                            saveTokenUseCase(resource.data.token!!)
                            appNavigator.popBackStack(AppRoute.AuthRoute, true)
                            appNavigator.navigate(AppRoute.BottomNavRoute)
                        } else {
                            setState {
                                copy(
                                    mobileError = resource.data?.message
                                )
                            }
                        }
                        setState {
                            copy(isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        setState {
                            copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        setState {
                            copy(isLoading = true)
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}