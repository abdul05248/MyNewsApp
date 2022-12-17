package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton


class LanguageRepository {

    fun getLanguages(): Flow<List<Language>>{

        return flow {
            emit(AppConstant.LANGUAGES)
        }

    }

}