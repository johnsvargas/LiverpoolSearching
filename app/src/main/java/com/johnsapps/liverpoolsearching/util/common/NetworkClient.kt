package com.johnsapps.liverpoolsearching.util.common

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    fun buildRetrofitClient(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .baseUrl("https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/")
            .build()
    }

    private fun buildOkHttpLoggerInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(buildOkHttpLoggerInterceptor())
        }.build()
    }

}
