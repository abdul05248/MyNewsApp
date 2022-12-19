package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.di.ActivityScope
import com.mynewsapp.mentor.di.api.NetworkService
import com.mynewsapp.mentor.utils.AppConstant.COUNTRIES
import com.mynewsapp.mentor.utils.AppConstant.COUNTRY
import com.mynewsapp.mentor.utils.AppConstant.COUNTRY_US
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class TopHeadlinesRepository constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(): Flow<List<Article>> {

        return flow { emit(networkService.getTopHeadlines(COUNTRY_US)) }
            .map { it.articles }
    }

}