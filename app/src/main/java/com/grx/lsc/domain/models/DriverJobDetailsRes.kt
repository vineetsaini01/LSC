package com.grx.lsc.domain.models

data class DriverJobDetailsRes(
    val `data`: Data?,
    val status: String?
)

data class Data(
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
    val weight: String?
)