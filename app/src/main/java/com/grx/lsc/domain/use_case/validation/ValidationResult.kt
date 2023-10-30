package com.grx.lsc.domain.use_case.validation

data class ValidationResult(
    val success: Boolean = false,
    val message: String? = null
)
