package com.example.android_finaluri.retrofit

import com.example.android_finaluri.retrofit.dto.ApiData
import com.example.android_finaluri.retrofit.dto.FieldsProto
import com.example.android_finaluri.retrofit.dto.Info
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResAPI {
    @GET("allDocuments")
    fun getInfo(@Query("collectionName")info: String):Call<List<ApiData<FieldsProto<Info>>>>
}