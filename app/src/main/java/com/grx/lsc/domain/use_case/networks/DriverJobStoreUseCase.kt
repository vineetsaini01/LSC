package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class DriverJobStoreUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        sealNo: String,
        id: String,
        containerNo: String,
        imagesVideos: String,
    ) = repository.driverJobStore(
        sealNo = sealNo,
        id = id,
        containerNo = containerNo,
        imagesVideos = imagesVideos
    )
}