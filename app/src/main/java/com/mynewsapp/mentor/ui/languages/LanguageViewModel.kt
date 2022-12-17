package com.mynewsapp.mentor.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.data.repository.LanguageRepository
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val languageRepository: LanguageRepository) : ViewModel() {

    private val _languageList = MutableStateFlow<Resource<List<Language>>>(Resource.loading())

    val languageList: StateFlow<Resource<List<Language>>> = _languageList

    init {
        getLanguages()
    }

    private fun getLanguages() {

        viewModelScope.launch {

            languageRepository.getLanguages().catch {
                _languageList.value = Resource.error(it.message)
            }
                .collect {
                    _languageList.value = Resource.success(it)
                }

        }

    }

}