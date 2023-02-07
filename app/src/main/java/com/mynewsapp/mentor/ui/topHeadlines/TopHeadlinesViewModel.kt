package com.mynewsapp.mentor.ui.topHeadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.ui.base.BaseViewModel
import com.mynewsapp.mentor.ui.base.UiState
import com.mynewsapp.mentor.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class TopHeadlinesViewModel(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
) :
    BaseViewModel() {

    private val _articleList = MutableStateFlow<UiState<List<TopHeadlines>>>(UiState.Loading)

    val articleList: StateFlow<UiState<List<TopHeadlines>>> = _articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {

        if (networkHelper.isNetworkConnected()) {
            fetchNewsFromNetworkAndSaveInLocal()
        } else {
            fetchNewsFromLocal()
        }

    }

    private fun fetchNewsFromLocal() {

        viewModelScope.launch(dispatcherProvider.main) {

            topHeadlinesRepository.getTopHeadlinesFromDb()
                .flowOn(dispatcherProvider.io)
                .catch {
                    _articleList.value = UiState.Error(this.toString())
                }
                .collect {
                    _articleList.value = UiState.Success(it)
                }
        }

    }

    private fun fetchNewsFromNetworkAndSaveInLocal() {

        viewModelScope.launch(dispatcherProvider.main) {

            topHeadlinesRepository.getTopHeadlines()
                .flowOn(dispatcherProvider.io)
                .catch {
                    _articleList.value = UiState.Error(it.toString())
                }
                .collect {
                    _articleList.value = UiState.Success(it)
                }

        }

    }
}