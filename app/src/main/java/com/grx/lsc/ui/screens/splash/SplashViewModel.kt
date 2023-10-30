package com.grx.lsc.ui.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.ui.navigation.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    lateinit var appNavController: NavController

    init {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            navigateToNextScreen()
        }, 3000)
    }

    private fun navigateToNextScreen() {
        val route = if (getTokenUseCase().isBlank()) {
            AppRoute.AuthRoute.route
        } else {
            AppRoute.BottomNavRoute.route
        }
        appNavController.navigate(route)
    }
}
