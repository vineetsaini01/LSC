package com.grx.lsc.utils

import android.app.Activity
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class GetMultipartPart @Inject constructor(@ApplicationContext private val activity: Activity) {

    operator fun invoke(
        uri: Uri,
        fileName: String = "file",
    ): MultipartBody.Part {
        val file = getFile(uri)
        val requestBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(fileName, file.name, requestBody)
    }

    private fun getFile(uri: Uri): File {
        val tsLong = System.currentTimeMillis() / 1000
        val child = tsLong.toString().plus(".png")
        val fileDir = activity.applicationContext?.filesDir
        val file = File(fileDir, child)
        val inputStream = activity.contentResolver?.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        return file
    }


}