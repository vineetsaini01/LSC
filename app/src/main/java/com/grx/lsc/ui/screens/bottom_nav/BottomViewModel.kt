package com.grx.lsc.ui.screens.bottom_nav

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.use_case.networks.DriverJobDetailsUseCase
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomViewModel @Inject constructor(
    val bottomNavigator: BottomNavigator,
    private val driverJobDetailsUseCase: DriverJobDetailsUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {
    var hasShowTopBottomNav by mutableStateOf(true)
    var isLoading by mutableStateOf(false)
    var driverJobDetailsRes: DriverJobDetailsRes? = null

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            hasShowTopBottomNav =
                destination.route != AppRoute.QRCode.route
        }


    fun setDestinationChangedListener() {
        bottomNavigator.navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    override fun onCleared() {
        bottomNavigator.navController.removeOnDestinationChangedListener(
            onDestinationChangedListener
        )
        super.onCleared()
    }

    init {
        driverJobDetails()
    }


    private fun driverJobDetails() {
        viewModelScope.launch(Dispatchers.IO) {

            driverJobDetailsUseCase("Bearer " + getTokenUseCase())
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {

                            isLoading = false
                            driverJobDetailsRes = resource.data

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
