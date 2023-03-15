package com.mynewsapp.mentor.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.model.topHeadines.TopHeadlinesResponse
import com.mynewsapp.mentor.data.paging.SearchPaging
import com.mynewsapp.mentor.di.api.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchRepository constructor(private val networkService: NetworkService) {

    fun getSearchResult(keyword: String, paging: Int, page: Int): Flow<List<Article>> {

        return flow {

            emit(
                networkService.getSearchResult(
                    keyword,
                    paging, page
                )
            )

        }
            .map {
                it.articles
            }

    }

    suspend fun getSearchResultWithoutFlow(
        keyword: String,
        paging: Int,
        page: Int
    ): TopHeadlinesResponse {
        Log.v("testPaging", "$keyword  -- $paging  --  $page")

        return networkService.getSearchResult(
            keyword,
            paging, page
        )

    }

    fun getSearchResultWithPaging(keyword: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {
            Log.v("testPaging", "getSearchResultWithPaging")

            SearchPaging(this, keyword)
        }
    ).flow

}