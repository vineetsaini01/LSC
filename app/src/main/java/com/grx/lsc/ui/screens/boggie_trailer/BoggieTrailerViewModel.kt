package com.grx.lsc.ui.screens.boggie_trailer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.grx.lsc.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoggieTrailerViewModel @Inject constructor() : ViewModel() {

    lateinit var navController: NavHostController


    var openDialog by mutableStateOf(true)
    var openDialog2 by mutableStateOf(false)

    fun onClickConfirm(){
        openDialog2 = true
        viewModelScope.launch {
            delay(2000)
            openDialog2 = false
            navController.navigate(AppScreens.EnterDetails.route)
        }
    }



}