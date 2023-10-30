package com.grx.lsc.domain.use_case.validation

import javax.inject.Inject


class ValidatePhoneNumber @Inject constructor() {

    operator fun invoke(phone: String): ValidationResult {
        return when {
            black(phone) -> ValidationResult(message = "Enter phone number")
            lessThanMinimum(phone) -> ValidationResult(message = "Enter 10 digits number")
            notContainsLetter(phone) -> ValidationResult(message = "Number can't contains letter")
            else -> ValidationResult(success = true)
        }
    }

    private fun black(it: String) = it.isBlank()

    private fun lessThanMinimum(it: String) = it.length < 10

    private fun notContainsLetter(it: String) = it.any { it.isLetter() }


}