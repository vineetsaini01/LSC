package com.grx.lsc.domain.use_case.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(private val fusedLocationClient: FusedLocationProviderClient) {
    @SuppressLint("MissingPermission")
    suspend operator fun invoke(): Location? {
        return try {
            val locationTask = fusedLocationClient.lastLocation
            awaitTask(locationTask)
        } catch (exception: Exception) {
            null
        }
    }

    private suspend fun awaitTask(locationTask: Task<Location>): Location? {
        return suspendCancellableCoroutine { continuation ->
            locationTask.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    continuation.resume(task.result)
                } else {
                    val exception =
                        task.exception ?: RuntimeException("Task failed without exception")
                    continuation.resumeWithException(exception)
                }
            }
        }
    }
}
