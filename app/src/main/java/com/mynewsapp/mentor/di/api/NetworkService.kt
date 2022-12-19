package com.mynewsapp.mentor.di.api

import com.mynewsapp.mentor.data.model.sources.SourcesResponse
import com.mynewsapp.mentor.data.model.topHeadines.TopHeadlinesResponse
import com.mynewsapp.mentor.utils.AppConstant.API_KEY

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse

    @GET("everything")
    suspend fun getSearchResult(@Query("q") search:String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsWithLanguageResult(@Query("language") language:String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsWithCountryResult(@Query("country") country:String): TopHeadlinesResponse

}