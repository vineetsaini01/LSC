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
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.ui.navigation.sharedViewModel
import com.grx.lsc.ui.screens.home.HomeViewModel
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
    private val buildImageBodyPart: BuildImageBodyPart,
    private val getTokenUseCase: GetTokenUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) :
    BaseViewModel<EnterDetailsContract.Event, EnterDetailsContract.State>(EnterDetailsContract.State()) {


    @Composable
    fun CameraLauncher() {

        val newLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview(),
        ) {
            it?.let {
                val mutableList: MutableList<Bitmap> = state.value.bitmaps.toMutableList()
                mutableList.add(it)
                setState {
                    copy(bitmaps = mutableList)
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
                val uri: Uri? =it.data?.data
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
                        state.value.bitmaps.map { bitmap ->
                            buildImageBodyPart(fileName = "", bitmap = bitmap)
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

                    val mutableList: MutableList<Bitmap> = state.value.bitmaps.toMutableList()
                    mutableList.remove(reqBitmap)
                    setState {
                        copy(bitmaps = mutableList)
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
                id = "1",
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
