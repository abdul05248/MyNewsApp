package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LanguageNewsRepository constructor(private val networkService: NetworkService) {

    fun getNewsWithLanguage(language: String): Flow<List<Article>> {

        return flow { emit(networkService.getNewsWithLanguageResult(language)) }
            .map { it.articles }
    }

    fun getNewsWithCountry(country: String): Flow<List<Article>> {

        return flow { emit(networkService.getNewsWithCountryResult(country)) }
            .map { it.articles }
    }

}