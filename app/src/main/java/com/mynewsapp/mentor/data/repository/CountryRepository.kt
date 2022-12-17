package com.mynewsapp.mentor.data.repository

import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

class CountryRepository {

    fun getCountries():Flow<List<Country>>{

        return flow {
            emit(AppConstant.COUNTRIES)
        }

    }

}