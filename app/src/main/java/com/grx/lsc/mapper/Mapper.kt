package com.grx.lsc.mapper

import com.grx.lsc.data.dto.DriverJobDetailsResDTO
import com.grx.lsc.domain.models.DriverJobDetailsRes

object Mapper {
    fun DriverJobDetailsResDTO.toDriverJobDetailsRes(): DriverJobDetailsRes {
        return DriverJobDetailsRes(data = data?.toData(), status = status)
    }
}