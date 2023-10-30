package com.grx.lsc.data.dto

import com.grx.lsc.domain.models.VerifyCodeRes

data class VerifyCodeResDTO(
    val message: String?,
    val token: String?
){
    fun  toVerifyCodeResponse():VerifyCodeRes{
        return VerifyCodeRes(
            message,
            token
        )
    }
}