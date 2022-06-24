package com.example.android_finaluri.retrofit.dto

import com.google.gson.annotations.SerializedName

data class ApiData<T> (
    @SerializedName("_fieldsProto")
    val fieldsProto: T?
)

data class FieldsProto<T> (
    val text1: T?,
    val text2: T?,
    val text3: T?
)

data class Info (
    val stringValue: String?,
    val valueType: String?
)