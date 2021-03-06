package com.radhsyn83.themoviedb.net

import com.radhsyn83.themoviedb.BuildConfig.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

//
// Created by Fathur Radhy 
// on May 2019-05-27.
//

object NetworkConfig {

    fun api(): ApiServices {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }

    private fun getInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)

        okHttp.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val original = chain.request()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
//                    .header("os", OS)
//
//                if (token != null) {
//                    requestBuilder.header("token", token)
//                }

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })

        return okHttp.build()
    }
}