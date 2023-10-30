package com.grx.lsc.data.dto

import com.grx.lsc.domain.models.JobStatusRes

data class JobStatusResDTO(
    val message: String?
){
    fun toJobStatusRes(): JobStatusRes {
        return JobStatusRes(message)
    }
}