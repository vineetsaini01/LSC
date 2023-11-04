package com.grx.lsc.ui.screens.enter_details

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.grx.lsc.core.BuildImageBodyPart
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.use_case.location.GetCurrentLocationUseCase
import com.grx.lsc.domain.use_case.networks.DriverJobStoreUseCase
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.ui.navigation.sharedViewModel
import com.grx.lsc.ui.screens.home.HomeViewModel
import com.grx.lsc.utils.BitmapToUri
import com.grx.lsc.utils.GetMultipartPart
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class EnterDetailsViewModel @Inject constructor(
    private val driverJobStoreUseCase: DriverJobStoreUseCase,
    private val bottomNavigator: BottomNavigator,
    private val getTokenUseCase: GetTokenUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val bitmapToUri: BitmapToUri,
    private val getMultipartPart: GetMultipartPart,
) :
    BaseViewModel<EnterDetailsContract.Event, EnterDetailsContract.State>(EnterDetailsContract.State()) {


    @Composable
    fun CameraLauncher() {

        val newLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview(),
        ) {
            it?.let {
                bitmapToUri(it)?.let { uri ->
                    val mutableUris: MutableList<Uri> = state.value.uris.toMutableList()
                    mutableUris.add(uri)
                    setState {
                        copy(uris = mutableUris)
                    }

                }

            }
        }
        setState {
            copy(cameraLauncher = newLauncher)
        }

    }

    @Composable
    fun DocsLauncher() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*text/csv*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        val launcher: ManagedActivityResultLauncher<Intent, ActivityResult> =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                it.data?.data?.let { uri ->
                    val mutableUris: MutableList<Uri> = state.value.uris.toMutableList()
                    mutableUris.add(uri)
                    setState {
                        copy(uris = mutableUris)
                    }

                }
            }

        setState {
            copy(docLauncher = launcher)
        }
    }

    @Composable
    fun SetSharedData(navBackStackEntry: NavBackStackEntry) {
        val driverJobDetailsRes =
            navBackStackEntry
                .sharedViewModel<HomeViewModel>(bottomNavigator.navController)
                .state.value.driverJobDetailsRes
        setState {
            copy(driverJobDetailsRes = driverJobDetailsRes)
        }
    }

    init {
        viewModelScope.launch {
            setState {
                copy(isLoading = true)
            }
            val location = getCurrentLocationUseCase()
            setState {
                copy(location = location, isLoading = false)
            }
        }
    }


    override fun event(event: EnterDetailsContract.Event) {
        event.apply {
            when (this) {
                is EnterDetailsContract.Event.OnChangedContainerNo -> {
                    setState {
                        copy(containerNo = newContainerNo)
                    }
                }

                is EnterDetailsContract.Event.OnChangedSealNo -> {
                    setState {
                        copy(sealNo = newSealNo)
                    }
                }

                EnterDetailsContract.Event.OnPressedDoneBtn -> {

                    val multipartBodyPartList: List<MultipartBody.Part> =
                        state.value.uris.map { uri ->
                            getMultipartPart(uri = uri)
                        }.toList()

                    driverJobStore(multipartBodyPartList = multipartBodyPartList)

                }

                EnterDetailsContract.Event.OnPressedUploadImage -> {
                    state.value.cameraLauncher?.launch()
                }

                EnterDetailsContract.Event.OnPressedUploadDoc -> {
                    val intent = Intent()
                        .setAction(Intent.ACTION_GET_CONTENT)
                        .setType("*text/csv*")
                        .addCategory(Intent.CATEGORY_OPENABLE)
                    state.value.docLauncher?.launch(intent)

                }

                is EnterDetailsContract.Event.OnPressedRemoveBitmap -> {

                    val mutableList: MutableList<Uri> = state.value.uris.toMutableList()
                    mutableList.remove(reqUri)
                    setState {
                        copy(uris = mutableList)
                    }
                }
            }
        }
    }

    private fun driverJobStore(
        multipartBodyPartList: List<MultipartBody.Part>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            driverJobStoreUseCase(
              //  id = "1",
                id = state.value.driverJobDetailsRes!!.data!!.id!!.toString(),
                containerNo = state.value.containerNo,
                imagesVideos = multipartBodyPartList,
                sealNo = state.value.sealNo,
                token = "Bearer ${getTokenUseCase()}",
                latitude = state.value.location?.latitude.toString(),
                longitude = state.value.location?.latitude.toString()
            )
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            setState {
                                copy(isLoading = false)
                            }
                            if (resource.data?.message == "Data updated successfully") {
                                bottomNavigator.navController.navigate(AppRoute.Reached.route)
                            }
                        }

                        is Resource.Error -> {
                            setState {
                                copy(isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            setState {
                                copy(isLoading = true)
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }
}
