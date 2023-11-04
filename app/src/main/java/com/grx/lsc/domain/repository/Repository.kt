package com.grx.lsc.domain.repository

import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.models.JobStatusRes
import com.grx.lsc.domain.models.SendVerificationCodeRes
import com.grx.lsc.domain.models.VerifyCodeRes
import com.grx.lsc.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.http.Field

interface Repository {

    suspend fun sendVerificationCode(mobile: String): Flow<Resource<SendVerificationCodeRes?>>
    suspend fun verifyCode(
        verificationCode: String,
        mobile: String,
    ): Flow<Resource<VerifyCodeRes?>>

    suspend fun driverJobDetails(token: String): Flow<Resource<DriverJobDetailsRes?>>
    suspend fun drierJobStatus(
        id: String,
        status: String,
        token: String,
    ): Flow<Resource<JobStatusRes?>>

    suspend fun uploadVehicleNumber(
        vehicleNumber: String,
        id: String,
        token: String,
    ): Flow<Resource<JobStatusRes?>>

    suspend fun driverJobStore(
        token: String,
        sealNo: String,
        id: String,
        containerNo: String,
        latitude: String,
        longitude: String,
        imagesVideos: List<MultipartBody.Part>,
    ): Flow<Resource<JobStatusRes?>>

}