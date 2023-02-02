package com.mynewsapp.mentor.ui.languages

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.repository.LanguageNewsRepository
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageNewsViewModel(private val languageNewsRepository: LanguageNewsRepository) :
    ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    fun fetchNewsWithLanguage(language: String) {

        Log.v("kjsjqs", language);

        viewModelScope.launch {

            languageNewsRepository.getNewsWithLanguage(language)
                .catch {
                    _articleList.value = Resource.error(this.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }

        }
    }

    fun fetchNewsWithCountry(country: String) {

        Log.v("kjsjqs", country);

        viewModelScope.launch {

            languageNewsRepository.getNewsWithCountry(country)
                .catch {
                    _articleList.value = Resource.error(this.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }

        }
    }


}