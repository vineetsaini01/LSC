package com.grx.lsc.domain.use_case.shared_pref

import com.grx.lsc.domain.repository.SharedPrefRepository
import javax.inject.Inject


class SaveTokenUseCase @Inject constructor(private val sharedPrefRepository: SharedPrefRepository) {
    operator fun invoke(token: String) {
        sharedPrefRepository.saveToken(token)
    }
}