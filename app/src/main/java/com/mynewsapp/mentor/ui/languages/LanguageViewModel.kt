package com.mynewsapp.mentor.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.data.repository.LanguageRepository
import com.mynewsapp.mentor.ui.base.BaseViewModel
import com.mynewsapp.mentor.ui.base.UiState
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val languageRepository: LanguageRepository) : BaseViewModel() {

    private val _languageList = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val languageList: StateFlow<UiState<List<Language>>> = _languageList

    init {
        getLanguages()
    }

    private fun getLanguages() {

        viewModelScope.launch {

            languageRepository.getLanguages().catch {
                _languageList.value = UiState.Error(it.message.toString())
            }
                .collect {
                    _languageList.value = UiState.Success(it)
                }

        }

    }

}