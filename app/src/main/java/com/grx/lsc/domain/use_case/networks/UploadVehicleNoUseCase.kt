package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class UploadVehicleNoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        vehicleNumber: String,
        id: String,
        token: String,
    ) = repository.uploadVehicleNumber(
        id = id,
        vehicleNumber = vehicleNumber,
        token = token
    )
}