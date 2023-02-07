package com.mynewsapp.mentor.ui.base

sealed interface UiState<out T> {

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>//Ask$ Why nothing not String

    object Loading : UiState<Nothing>

}