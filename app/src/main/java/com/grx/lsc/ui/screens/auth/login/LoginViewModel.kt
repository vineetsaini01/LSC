package com.grx.lsc.ui.screens.auth.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.grx.lsc.activity.HomeActivity
import com.grx.lsc.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    lateinit var navController: NavHostController

    lateinit var gotoHomeActivity: () -> Unit

    var mobileN0 by mutableStateOf("")
    var mobileOtp by mutableStateOf("")

    fun sendOtp() {
        navController.navigate(AppScreens.LoginOtp.route)
    }

    fun backPress() {
        navController.popBackStack()
    }

    fun loginBtn(context: Context) {
        context.startActivity(
            Intent(context, HomeActivity::class.java)
        )
    }

}