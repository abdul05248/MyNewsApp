package com.mynewsapp.mentor.di.api

import com.mynewsapp.mentor.utils.AppConstant
import com.mynewsapp.mentor.utils.AppConstant.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApplicationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("X-Api-Key", API_KEY)
        val request=requestBuilder.build()

        return chain.proceed(request)
    }
}