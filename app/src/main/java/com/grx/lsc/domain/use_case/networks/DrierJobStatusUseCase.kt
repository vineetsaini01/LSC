package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class DrierJobStatusUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        id: String,
        status: String,
        token: String,
    ) = repository.drierJobStatus(
        id = id,
        status = status,
        token = token
    )
}