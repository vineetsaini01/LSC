package com.grx.lsc.ui.navigation

import javax.inject.Inject
import javax.inject.Singleton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

open class Navigator {
    lateinit var navController: NavHostController
    @Composable
    fun SetNavController() {
        if (!this::navController.isInitialized) {
            this.navController = rememberNavController()
        }
    }
}


@Singleton
class AppNavigator @Inject constructor() : Navigator()
@Singleton
class BottomNavigator @Inject constructor() : Navigator()