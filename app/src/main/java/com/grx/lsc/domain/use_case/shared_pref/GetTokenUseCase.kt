package com.grx.lsc.domain.use_case.shared_pref

import com.grx.lsc.domain.repository.SharedPrefRepository
import javax.inject.Inject


class GetTokenUseCase @Inject constructor(private val sharedPrefRepository: SharedPrefRepository) {
    operator fun invoke(): String {
        return sharedPrefRepository.getToken()
    }
}