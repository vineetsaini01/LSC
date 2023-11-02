package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class VerifyCodeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        verificationCode: String,
        mobile: String,
    ) = repository.verifyCode(
        mobile = mobile,
        verificationCode = verificationCode
    )
}