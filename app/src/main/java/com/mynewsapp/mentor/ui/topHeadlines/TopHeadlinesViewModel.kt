package com.mynewsapp.mentor.ui.topHeadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.utils.AppConstant.COUNTRY
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlinesViewModel(private val topHeadlinesRepository: TopHeadlinesRepository) :
    ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList:StateFlow<Resource<List<Article>>> =_articleList

    init {

        fetchNews()
    }

    private fun fetchNews() {

        viewModelScope.launch {

            topHeadlinesRepository.getTopHeadlines()
                .catch {
                    _articleList.value=Resource.error(this.toString())
                }
                .collect{
                _articleList.value=Resource.success(it)
                }

        }
    }

}