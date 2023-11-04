package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import okhttp3.MultipartBody
import javax.inject.Inject


class DriverJobStoreUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        sealNo: String,
        id: String,
        containerNo: String,
        latitude: String,
        longitude: String,
        imagesVideos: List<MultipartBody.Part>,
        token: String,
    ) = repository.driverJobStore(
        sealNo = sealNo,
        id = id,
        containerNo = containerNo,
        imagesVideos = imagesVideos,
        token = token,
        longitude = longitude,
        latitude = latitude
    )
}