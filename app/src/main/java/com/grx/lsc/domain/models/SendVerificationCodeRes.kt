package com.grx.lsc.domain.models

data class SendVerificationCodeRes(
    val access_token: String?,
    val message: String?,
    val token_type: String?
)