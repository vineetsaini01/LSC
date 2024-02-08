package com.grx.lsc.domain.use_case.networks

import com.grx.lsc.domain.repository.Repository
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        mobile: String,
        password: String,
    ) = repository.login(
        mobile = mobile,
        password = password
    )
}