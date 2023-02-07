package com.mynewsapp.mentor.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.data.repository.CountryRepository
import com.mynewsapp.mentor.ui.base.BaseViewModel
import com.mynewsapp.mentor.ui.base.UiState
import com.mynewsapp.mentor.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel (private val countryRepository: CountryRepository) : BaseViewModel() {

    private val _countryList = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val countryList : StateFlow<UiState<List<Country>>> = _countryList

    //first init call hoga? then upar wali line
    init {
        fetchCountries()
    }

    private fun fetchCountries() {

        viewModelScope.launch {
            countryRepository.getCountries()
                .catch {
                    _countryList.value=UiState.Error(it.message.toString())

                }
                .collect{
                    _countryList.value=UiState.Success(it)
                }
        }
    }
}