package com.grx.lsc.ui.screens.boggie_trailer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoggieTrailerViewModel @Inject constructor(
    private val repository: Repository,
    private val bottomNavigator: BottomNavigator,
) : BaseViewModel<BoggieTrailerEvent>() {


    var driverJobDetailsRes: DriverJobDetailsRes? = null

    var openDialog by mutableStateOf(true)
    var openDialog2 by mutableStateOf(false)


    override fun onEvent(event: BoggieTrailerEvent) {
        event.apply {
            when (this) {
                BoggieTrailerEvent.OnPressedChange -> {

                }
                BoggieTrailerEvent.OnPressedYesConfirmBtn -> {
                    openDialog2 = true
                    viewModelScope.launch {
                        delay(2000)
                        openDialog2 = false
                        bottomNavigator.navController.navigate(AppRoute.EnterDetails.route)
                    }
                }
            }
        }
    }


}