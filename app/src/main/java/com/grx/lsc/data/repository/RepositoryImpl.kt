package com.grx.lsc.data.repository

import com.grx.lsc.data.remote.ApiService
import com.grx.lsc.domain.models.DriverJobDetailsRes
import com.grx.lsc.domain.models.JobStatusRes
import com.grx.lsc.domain.models.SendVerificationCodeRes
import com.grx.lsc.domain.models.VerifyCodeRes
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.mapper.Mapper.toDriverJobDetailsRes
import com.grx.lsc.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiService,
) : Repository {

    override suspend fun sendVerificationCode(mobile: String): Flow<Resource<SendVerificationCodeRes?>> =
        flow {

            emit(Resource.Loading())
            val remoteData = try {
                apiHelper.sendVerificationCode(mobile)
            } catch (e: HttpException) {
                emit(Resource.Error(message = "An unexpected error occurred"))
                null
            } catch (e: IOException) {
                emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
                null
            }
            remoteData?.let { data ->
                emit(Resource.Success(data = data.toSendVerificationCodeResponse()))
            }
        }

    override suspend fun verifyCode(
        verificationCode: String,
        mobile: String,
    ): Flow<Resource<VerifyCodeRes?>> = flow {
        emit(Resource.Loading())
        val remoteData = try {
            apiHelper.verifyCode(mobile = mobile, verificationCode = verificationCode)
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
            null
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
            null
        }
        remoteData?.let { data ->
            emit(Resource.Success(data = data.toVerifyCodeResponse()))
        }
    }

    override suspend fun driverJobDetails(token: String): Flow<Resource<DriverJobDetailsRes?>> =
        flow {
            emit(Resource.Loading())
            val remoteData = try {
                apiHelper.driverJobDetails(token)
            } catch (e: HttpException) {
                emit(Resource.Error(message = "An unexpected error occurred"))
                null
            } catch (e: IOException) {
                emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
                null
            }
            remoteData?.let { data ->
                emit(Resource.Success(data = data.toDriverJobDetailsRes()))
            }
        }

    override suspend fun drierJobStatus(
        id: String,
        status: String,
        token: String,
        reason: String?,
    ): Flow<Resource<JobStatusRes?>> = flow {
        emit(Resource.Loading())
        val remoteData = try {
            apiHelper.drierJobStatus(id = id, status = status, token = token, reason = reason)
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
            null
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
            null
        }
        remoteData?.let { data ->
            emit(Resource.Success(data = data.toJobStatusRes()))
        }
    }

    override suspend fun uploadVehicleNumber(
        vehicleNumber: String,
        id: String,
        token: String,
    ): Flow<Resource<JobStatusRes?>> = flow {
        emit(Resource.Loading())
        val remoteData = try {
            apiHelper.uploadVehicleNumber(vehicleNumber = vehicleNumber, id = id,token=token)
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
            null
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
            null
        }
        remoteData?.let { data ->
            emit(Resource.Success(data = data.toJobStatusRes()))
        }
    }

    override suspend fun driverJobStore(
        token: String,
        sealNo: String,
        id: String,
        containerNo: String,
        latitude: String,
        longitude: String,
        imagesVideos: List<MultipartBody.Part>
    ): Flow<Resource<JobStatusRes?>> = flow {
        emit(Resource.Loading())
        val remoteData = try {
            val idNew = RequestBody.create("text/plain".toMediaType(), id)
            val sealNoNew = RequestBody.create("text/plain".toMediaType(), sealNo)
            val containerNew = RequestBody.create("text/plain".toMediaType(), containerNo)
            val latitudeNew = RequestBody.create("text/plain".toMediaType(), latitude)
            val longitudeNew = RequestBody.create("text/plain".toMediaType(), longitude)

            apiHelper.driverJobStore(
                token = token,
                sealNo = sealNoNew,
                id = idNew,
                containerNo = containerNew,
                images = imagesVideos,
                latitude = latitudeNew,
                longitude = longitudeNew
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
            null
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. check you internet connection"))
            null
        }
        remoteData?.let { data ->
            emit(Resource.Success(data = data.toJobStatusRes()))
        }
    }


}