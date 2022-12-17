package com.mynewsapp.mentor.ui.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.sources.Source
import com.mynewsapp.mentor.data.repository.NewsSourceRepository
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsSourceViewModel (private val newsSourceRepository: NewsSourceRepository) :ViewModel(){

    private val _sourceList = MutableStateFlow<Resource<List<Source>>>(Resource.loading())

    val sourceList : StateFlow<Resource<List<Source>>> = _sourceList

    init {
        fetchSources()
    }

    private fun fetchSources() {

        viewModelScope.launch {
            newsSourceRepository.getSources()
                .catch {
                    _sourceList.value=Resource.error(it.message)
                }
                .collect{
                  _sourceList.value=Resource.success(it)
                }
        }

    }

}