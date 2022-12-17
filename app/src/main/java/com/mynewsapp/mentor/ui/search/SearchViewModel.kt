package com.mynewsapp.mentor.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.repository.SearchRepository
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _searchList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val searchList: StateFlow<Resource<List<Article>>> = _searchList

    init {
        fetchResult()
    }

    private fun fetchResult() {

        viewModelScope.launch {
            searchRepository.getSearchResult("s")
                .catch {
                    _searchList.value = Resource.error(it.message)
                }
                .collect {
                    _searchList.value = Resource.success(it)
                }
        }

    }

}