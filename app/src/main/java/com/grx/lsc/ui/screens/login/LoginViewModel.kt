package com.grx.lsc.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.grx.lsc.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    lateinit var navController: NavHostController

    var mobileN0 by mutableStateOf("")
    var mobileOtp by mutableStateOf("")

    fun sendOtp() {
        navController.navigate(AppScreens.LoginOtp.route)
    }

    fun backPress() {
        navController.popBackStack()
    }

    fun loginBtn() {
        navController.navigate(AppScreens.Home.route) {
            popUpTo(route = AppScreens.Login.route){
                inclusive=true
            }
        }
    }

}