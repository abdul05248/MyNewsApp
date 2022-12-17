package com.mynewsapp.mentor.di.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    private const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "7502ab9197514f9792f796df2b3c35d7"

    fun create():NetworkService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

}