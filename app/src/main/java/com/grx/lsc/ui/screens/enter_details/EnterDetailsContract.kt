package com.grx.lsc.ui.screens.enter_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grx.lsc.domain.models.DriverJobDetailsRes


class EnterDetailsContract {

    sealed class Event {
        object OnPressedDoneBtn : Event()

        data class OnChangedContainerNo(val newContainerNo: String) : Event()
        data class OnChangedSealNo(val newSealNo: String) : Event()

        object OnPressedUploadImage : Event()
        object OnPressedUploadDoc : Event()
    }

    data class State(
        val containerNo: String = "",
        val sealNo: String = "",
        val isLoading: Boolean = false,
        var driverJobDetailsRes: DriverJobDetailsRes? = null
    )


}