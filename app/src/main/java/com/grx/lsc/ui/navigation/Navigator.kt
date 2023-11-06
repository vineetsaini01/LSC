package com.grx.lsc.ui.navigation

import javax.inject.Inject
import javax.inject.Singleton
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import java.lang.ref.WeakReference

open class Navigator {

    private var navControllerRef: WeakReference<NavHostController>? = null

    fun getNavController(): NavHostController? = navControllerRef?.get()

    fun setNavController(navController: NavHostController, owner: LifecycleOwner) {
        if (navControllerRef == null) {
            navControllerRef = WeakReference(navController)
            navController.setLifecycleOwner(owner)
        }
    }

    fun popBackStack() {
        navControllerRef?.get()?.popBackStack()
    }

    fun popBackStack(route: AppRoute, inclusive: Boolean = false) {
        navControllerRef?.get()?.popBackStack(route = route.route, inclusive = inclusive)
    }

    fun navigate(route: AppRoute) {
        navControllerRef?.get()?.navigate(route.route)
    }


}


@Singleton
class AppNavigator @Inject constructor() : Navigator()

@Singleton
class BottomNavigator @Inject constructor() : Navigator()
