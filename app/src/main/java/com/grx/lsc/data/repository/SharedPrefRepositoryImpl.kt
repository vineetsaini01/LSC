package com.grx.lsc.data.repository

import com.grx.lsc.data.shared_pref_storage.SharedPrefStorage
import com.grx.lsc.domain.repository.SharedPrefRepository
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(private val sharedPrefStorage: SharedPrefStorage) :
    SharedPrefRepository {

    companion object {
        const val TOKEN_KEY = "token_key"
    }

    override fun getToken(): String {
        return sharedPrefStorage.getString(TOKEN_KEY, "")
    }

    override fun saveToken(token: String) {
        sharedPrefStorage.saveString(TOKEN_KEY, token)
    }

    override fun logout() {
        sharedPrefStorage.clearSharedPreferences()
    }
}

