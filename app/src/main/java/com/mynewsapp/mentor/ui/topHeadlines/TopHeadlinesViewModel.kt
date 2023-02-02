package com.mynewsapp.mentor.ui.topHeadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.utils.AppConstant.COUNTRY
import com.mynewsapp.mentor.utils.DispatcherProvider
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TopHeadlinesViewModel(private val topHeadlinesRepository: TopHeadlinesRepository,
            private val networkHelper: NetworkHelper,
            private val dispatcherProvider: DispatcherProvider) :
    ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<TopHeadlines>>>(Resource.loading())

    val articleList:StateFlow<Resource<List<TopHeadlines>>> =_articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {

        if (networkHelper.isNetworkConnected()){
            fetchNewsFromNetworkAndSaveInLocal()
        }
        else{
            fetchNewsFromLocal()
        }

    }

    private fun fetchNewsFromLocal(){

        viewModelScope.launch (dispatcherProvider.main){

            topHeadlinesRepository.getTopHeadlinesFromDb()
                .flowOn(dispatcherProvider.io)
                .catch {
                    _articleList.value = Resource.error(this.toString())
                }
                .collect{
                    _articleList.value = Resource.success(it)
                }
        }

    }

    private fun fetchNewsFromNetworkAndSaveInLocal(){

        viewModelScope.launch(dispatcherProvider.main) {

            topHeadlinesRepository.getTopHeadlines()
                .flowOn(dispatcherProvider.io)
                .catch {
                _articleList.value= Resource.error(it.toString())
                }
                .collect{
                    _articleList.value =Resource.success(it)
                }

        }

    }
}