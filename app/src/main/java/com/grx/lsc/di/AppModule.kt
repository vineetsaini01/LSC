package com.grx.lsc.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.grx.lsc.data.remote.ApiService
import com.grx.lsc.utils.Constants.BASE_URL
import com.grx.lsc.data.repository.RepositoryImpl
import com.grx.lsc.data.repository.SharedPrefRepositoryImpl
import com.grx.lsc.data.shared_pref_storage.SharedPrefStorage
import com.grx.lsc.domain.repository.Repository
import com.grx.lsc.domain.repository.SharedPrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(mHttpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiHelper = apiService)
    }

    @Singleton
    @Provides
    fun providesSharedPrefRepository(sharedPrefStorage: SharedPrefStorage): SharedPrefRepository {
        return SharedPrefRepositoryImpl(sharedPrefStorage = sharedPrefStorage)
    }


    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}