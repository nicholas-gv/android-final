package com.example.android_finaluri.retrofit

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun initClient() {
        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://v1.nocodeapi.com/flopi/fbsdk/WVhwFIwyhWIxAfgv/firestore/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    }

    private fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    val reqResApi: ReqResAPI
        get() = createService(ReqResAPI::class.java)

}