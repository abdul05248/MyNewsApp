package com.mynewsapp.mentor.ui.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.sources.Source
import com.mynewsapp.mentor.data.repository.NewsSourceRepository
import com.mynewsapp.mentor.ui.base.BaseViewModel
import com.mynewsapp.mentor.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsSourceViewModel (private val newsSourceRepository: NewsSourceRepository) :BaseViewModel(){

    private val _sourceList = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)

    val sourceList : StateFlow<UiState<List<Source>>> = _sourceList

    init {
        fetchSources()
    }

    private fun fetchSources() {

        viewModelScope.launch {
            newsSourceRepository.getSources()
                .catch {
                    _sourceList.value=UiState.Error(it.message.toString())
                }
                .collect{
                  _sourceList.value=UiState.Success(it)
                }
        }

    }

}