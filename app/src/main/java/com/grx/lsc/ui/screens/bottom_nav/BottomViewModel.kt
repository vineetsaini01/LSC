package com.grx.lsc.ui.screens.bottom_nav

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomViewModel @Inject constructor(
    val bottomNavigator: BottomNavigator,
) : ViewModel()
{
    var hasShowTopBottomNav by mutableStateOf(true)

    private val onDestinationChangedListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        hasShowTopBottomNav = destination.route != AppRoute.QRCode.route // Replace "QrCode" with your actual route name
    }



    fun initData() {
        bottomNavigator.navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    // Remember to remove the listener when the ViewModel is no longer used to prevent memory leaks
    override fun onCleared() {
        bottomNavigator.navController.removeOnDestinationChangedListener(onDestinationChangedListener)
        super.onCleared()
    }
}
