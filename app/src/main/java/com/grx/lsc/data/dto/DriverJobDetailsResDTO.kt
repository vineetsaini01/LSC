package com.grx.lsc.data.dto

import com.grx.lsc.domain.models.Data

data class DriverJobDetailsResDTO(
    val `data`: DataDTO?,
    val status: String?,
)

data class DataDTO(
    val boggie_number: String?,
    val created_at: String?,
    val doc: String?,
    val documents: Any?,
    val emirate: String?,
    val id: Int?,
    val job_created_at: String?,
    val job_number: String?,
    val status: String?,
    val trailer_number: String?,
    val transport_id: String?,
    val updated_at: String?,
    val vehicle_driver_id: String?,
    val vehicle_number: Any?,
    val weight: String?,
) {
    fun toData(): Data {
        return Data(
            boggie_number = boggie_number,
            created_at = created_at,
            doc = doc,
            documents = documents,
            emirate = emirate,
            id = id,
            job_created_at = job_created_at,
            job_number = job_number,
            status = status,
            trailer_number = trailer_number,
            transport_id = transport_id,
            updated_at = updated_at,
            vehicle_driver_id = vehicle_driver_id,
            vehicle_number = vehicle_number,
            weight = weight
        )
    }
}