package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class DriverJobDetailsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        token: String,
    ) = repository.driverJobDetails(
        token = token
    )
}