package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class SendVerificationCodeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        mobile: String,
    ) = repository.sendVerificationCode(
        mobile = mobile
    )
}