package com.mynewsapp.mentor.di.api

import com.mynewsapp.mentor.data.model.sources.SourcesResponse
import com.mynewsapp.mentor.data.model.topHeadines.TopHeadlinesResponse
import com.mynewsapp.mentor.di.api.Networking.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesWithCountry(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesWithLanguage(@Query("language") language: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getSearchResult(@Query("q") search:String): TopHeadlinesResponse

}