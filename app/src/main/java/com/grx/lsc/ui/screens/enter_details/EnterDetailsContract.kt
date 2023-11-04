package com.grx.lsc.ui.screens.enter_details

import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.grx.lsc.domain.models.DriverJobDetailsRes


class EnterDetailsContract {

    sealed class Event {
        object OnPressedDoneBtn : Event()

        data class OnChangedContainerNo(val newContainerNo: String) : Event()
        data class OnChangedSealNo(val newSealNo: String) : Event()

        object OnPressedUploadImage : Event()
        object OnPressedUploadDoc : Event()

        data class OnPressedRemoveBitmap(val reqUri: Uri) : Event()
    }

    data class State(
        val containerNo: String = "456763",
        val sealNo: String = "877675",
        val isLoading: Boolean = false,
        var driverJobDetailsRes: DriverJobDetailsRes? = null,
        var cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?>? = null,
        val docLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>? = null,
        val uris: List<Uri> = listOf(),
        val location: Location? = null,
    )


}