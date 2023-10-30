package com.grx.lsc.data.shared_pref_storage

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPrefStorage @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("app_shared_preferences", Context.MODE_PRIVATE)
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}

