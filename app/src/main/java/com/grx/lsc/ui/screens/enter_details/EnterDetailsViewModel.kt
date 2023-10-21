package com.grx.lsc.ui.screens.enter_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.grx.lsc.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterDetailsViewModel @Inject constructor() : ViewModel() {

    lateinit var navController: NavHostController

    var containerNo by mutableStateOf("")
    var sealNo by mutableStateOf("")


    fun onPressedDone(){
        navController.navigate(AppScreens.Reached.route)
    }




}