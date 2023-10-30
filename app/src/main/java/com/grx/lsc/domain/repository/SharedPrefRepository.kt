package com.grx.lsc.domain.repository

interface SharedPrefRepository {
    fun getToken(): String
    fun saveToken(token: String)
    fun logout()
}