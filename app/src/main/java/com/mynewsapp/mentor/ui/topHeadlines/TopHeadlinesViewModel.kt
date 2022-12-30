package com.mynewsapp.mentor.ui.topHeadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.utils.AppConstant.COUNTRY
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//Ask$ activity me inject hai to yha pe constructor me sab mil jaega
class TopHeadlinesViewModel(private val topHeadlinesRepository: TopHeadlinesRepository,
            private val networkHelper: NetworkHelper) :
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

        viewModelScope.launch {

            topHeadlinesRepository.getTopHeadlinesFromDb()
                .flowOn(Dispatchers.IO)
                .catch {
                    _articleList.value = Resource.error(this.toString())
                }
                .collect{
                    _articleList.value = Resource.success(it)
                }
        }

    }

    private fun fetchNewsFromNetworkAndSaveInLocal(){

        viewModelScope.launch(Dispatchers.Main) {

            topHeadlinesRepository.getTopHeadlines()
                .flowOn(Dispatchers.IO)
                .catch {
                _articleList.value= Resource.error(this.toString())
                }
                .collect{
                    _articleList.value =Resource.success(it)
                }

        }

    }
}