package com.grx.lsc.data.remote

import com.grx.lsc.data.dto.DriverJobDetailsResDTO
import com.grx.lsc.data.dto.JobStatusResDTO
import com.grx.lsc.data.dto.SendVerificationCodeResDTO
import com.grx.lsc.data.dto.VerifyCodeResDTO
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {


    @FormUrlEncoded
    @POST("send-verification-code")
    suspend fun sendVerificationCode(
        @Field("mobile") mobile: String,
    ): SendVerificationCodeResDTO

    @FormUrlEncoded
    @POST("verify-code")
    suspend fun verifyCode(
        @Field("verification_code") verificationCode: String,
        @Field("mobile") mobile: String,
    ): VerifyCodeResDTO


    @GET("driver")
    suspend fun driverJobDetails(@Header("Authorization") token: String): DriverJobDetailsResDTO


    @FormUrlEncoded
    @POST("drier-job-status")
    suspend fun drierJobStatus(
        @Header("Authorization") token: String,
        @Field("id") id: String,
        @Field("status") status: String,
        @Field("reason") reason: String? = null,
    ): JobStatusResDTO

    @FormUrlEncoded
    @POST("driver-get-vehicle-number")
    suspend fun uploadVehicleNumber(
        @Header("Authorization") token: String,
        @Field("vehicle_number") vehicleNumber: String,
        @Field("id") id: String,
    ): JobStatusResDTO

    @FormUrlEncoded
    @POST("driver-job-store")
    suspend fun driverJobStore(
        @Header("Authorization") token: String,
        @Field("seal_no") sealNo: String,
        @Field("id") id: String,
        @Field("container_no") containerNo: String,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
        @Field("images_and_videos") imagesVideos: List<MultipartBody.Part>,
    ): JobStatusResDTO
}
