package com.grx.lsc.data.dto

import com.grx.lsc.domain.models.LoginRes

data class LoginResDTO(
    val message: String?,
    val token: String?
) {
    fun toLoginRes(): LoginRes {
        return LoginRes(message, token)
    }
}