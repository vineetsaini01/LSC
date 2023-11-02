package com.grx.lsc.ui.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grx.lsc.core.base_view_model.BaseViewModel
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.domain.use_case.shared_pref.GetTokenUseCase
import com.grx.lsc.domain.use_case.shared_pref.LogoutUseCase
import com.grx.lsc.ui.navigation.AppNavigator
import com.grx.lsc.ui.navigation.AppRoute
import com.grx.lsc.ui.navigation.BottomNavigator
import com.grx.lsc.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val getTokenUseCase: GetTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val appNavigator: AppNavigator,
    private val bottomNavigator: BottomNavigator,
) : BaseViewModel<HomeEvent>() {


    var isLoading by mutableStateOf(false)
    var hasExpended by mutableStateOf(false)
    var driverJobDetailsRes by mutableStateOf<DriverJobDetailsRes?>(null)


    init {
        driverJobDetails()
    }

    override fun onEvent(event: HomeEvent) {
        event.apply {
            when (this) {
                is HomeEvent.OnPressedAcceptOrReject -> {
                    drierJobStatus(status = status)
                }

                is HomeEvent.OnPressedDocDownload -> {
                    val link = driverJobDetailsRes?.data?.doc!!
                    val destination = File("/path/to/destination/filename.pdf")
                    downloadFile(link, destination)
                }

                is HomeEvent.OnPressedQrCode -> {
                    bottomNavigator.navController.navigate(AppRoute.QRCode.route)
                }
            }
        }
    }


    private fun downloadFile(url: String, destination: File) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    // Handle unsuccessful response
                }

                val responseBody = response.body
                responseBody?.let { saveToFile(it, destination) }
            }
        })
    }

    private fun saveToFile(responseBody: ResponseBody, destination: File) {
        val inputStream = responseBody.byteStream()
        val outputStream = FileOutputStream(destination)

        try {
            val buffer = ByteArray(4096)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream.close()
            outputStream.close()
        }
    }


    private fun driverJobDetails() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.driverJobDetails("Bearer " + getTokenUseCase())
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            isLoading = false
                            if (resource.data?.status == "success") {
                                driverJobDetailsRes = resource.data
                            }
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

    private fun drierJobStatus(status: String) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.drierJobStatus(
                token = "Bearer " + getTokenUseCase(),
                id = driverJobDetailsRes!!.data!!.id!!.toString(),
                status = status
            ).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        isLoading = false
                        if (resource.data?.message == "Status updated successfully") {
                            if (status == "accept") {
                                hasExpended = true
                            } else {
                                logoutUseCase()
                                appNavigator.navController.popBackStack(
                                    route = AppRoute.BottomNavRoute.route,
                                    inclusive = true
                                )
                                appNavigator.navController.navigate(AppRoute.AuthRoute.route)
                            }
                        }
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