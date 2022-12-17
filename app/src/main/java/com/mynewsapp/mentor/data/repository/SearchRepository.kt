package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchRepository constructor(private val networkService: NetworkService) {

    fun getSearchResult(keyword: String): Flow<List<Article>> {

        return flow {

            emit(networkService.getSearchResult(keyword))

        }
            .map {
                it.articles
            }

    }

}