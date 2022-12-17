package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.sources.Source
import com.mynewsapp.mentor.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


class NewsSourceRepository constructor(private val networkService: NetworkService) {

    fun getSources(): Flow<List<Source>> {

        return flow {
            emit(networkService.getSources())
        }.map {
            it.sources
        }
    }

}