package com.grx.lsc.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.domain.repository.SharedPrefRepository
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.domain.use_case.shared_pref.SaveTokenUseCase
import com.grx.lsc.domain.use_case.validation.ValidateOtp
import com.grx.lsc.domain.use_case.validation.ValidatePhoneNumber
import com.grx.lsc.ui.navigation.AppDestination
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
    private val repository: Repository,
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val validateOtp: ValidateOtp,
    private val saveTokenUseCase: SaveTokenUseCase,
) : BaseViewModel<LoginEvent>() {

    lateinit var navController: NavHostController

    var mobileN0 by mutableStateOf("")
    var mobileError by mutableStateOf<String?>(null)
    var mobileOTPError by mutableStateOf<String?>(null)
    var mobileOtp by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    override fun onEvent(event: LoginEvent) {
        event.apply {
            when (this) {
                is LoginEvent.OnChangedMobileNumber -> {
                    mobileN0 = newMobileNo
                    mobileError = null
                }

                is LoginEvent.OnChangedMobileOTP -> {
                    mobileOtp = newMobileOTP
                    mobileError = null
                }

                is LoginEvent.SendOtp -> {
                    val phoneNumberResult = validatePhoneNumber.invoke(mobileN0)
                    if (!phoneNumberResult.success) {
                        mobileError = phoneNumberResult.message
                        return
                    }
                    sendVerificationCode()
                }

                LoginEvent.VerifyCode -> {
                    val phoneNumberResult = validateOtp.invoke(mobileOtp)
                    if (!phoneNumberResult.success) {
                        mobileOTPError = phoneNumberResult.message
                        return
                    }
                    verifyCode()
                }

                is LoginEvent.OnBackPress -> {
                    navController.popBackStack()
                }
            }
        }
    }


    private fun sendVerificationCode() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.sendVerificationCode(mobile = "+91$mobileN0").onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data?.message == "Verification code sent")
                            navController.navigate(AppDestination.LoginOtp.route)
                        else mobileError = resource.data?.message
                        isLoading = false
                    }

                    is Resource.Error -> {
                        isLoading = false
                    }

                    is Resource.Loading -> {
                        isLoading = true
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    private fun verifyCode() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.verifyCode(
                verificationCode = mobileOtp,
                mobile = "+91$mobileN0"
            ).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data?.message == "Login successful") {
                            saveTokenUseCase(resource.data.token!!)
                            navController.popBackStack(AppRoute.AuthRoute.route, true)
                            navController.navigate(AppRoute.BottomNavRoute.route)
                        } else {
                            mobileError = resource.data?.message
                        }
                        isLoading = false
                    }

                    is Resource.Error -> {
                        isLoading = false
                    }

                    is Resource.Loading -> {
                        isLoading = true
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}