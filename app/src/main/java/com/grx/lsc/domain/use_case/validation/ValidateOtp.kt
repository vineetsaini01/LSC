package com.grx.lsc.domain.use_case.validation

import javax.inject.Inject


class ValidateOtp @Inject constructor() {

    operator fun invoke(otp: String): ValidationResult {
        return when {
            black(otp) -> ValidationResult(message = "Enter OTP")
            lessThanMinimum(otp) -> ValidationResult(message = "Enter valid OTP")
            containsLetter(otp) -> ValidationResult(message = "OTP can't not allow letter")
            else -> ValidationResult(success = true)
        }
    }
    private fun black(it: String) = it.isBlank()
    private fun lessThanMinimum(it: String) = it.length != 6
    private fun containsLetter(it: String) = it.any { it.isLetter() }
}