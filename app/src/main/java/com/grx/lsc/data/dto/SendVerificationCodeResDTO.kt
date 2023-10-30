package com.grx.lsc.data.dto

import com.grx.lsc.domain.models.SendVerificationCodeRes

data class SendVerificationCodeResDTO(
    val access_token: String?,
    val message: String?,
    val token_type: String?,
) {
    fun toSendVerificationCodeResponse(): SendVerificationCodeRes {
        return SendVerificationCodeRes(
            access_token = access_token,
            message = message,
            token_type = token_type
        )
    }
}