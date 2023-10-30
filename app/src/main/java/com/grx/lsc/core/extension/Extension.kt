package com.grx.lsc.core.extension

fun String?.toTempUrl(): String {
    return this?.replace("/", "*") ?: ""
}
fun String?.toOrgUrl(): String {
    return this?.replace("*", "/") ?: ""
}