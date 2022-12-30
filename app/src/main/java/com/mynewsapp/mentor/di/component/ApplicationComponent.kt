package com.mynewsapp.mentor.di.component

import android.content.Context
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.local.database.DatabaseHelperImpl
import com.mynewsapp.mentor.data.repository.CountryRepository
import com.mynewsapp.mentor.data.repository.LanguageRepository
import com.mynewsapp.mentor.data.repository.NewsSourceRepository
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.ApplicationContext
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.di.api.NetworkService
import com.mynewsapp.mentor.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDbHelper(): DatabaseHelperImpl

    fun getNetworkHelper(): NetworkHelper

}