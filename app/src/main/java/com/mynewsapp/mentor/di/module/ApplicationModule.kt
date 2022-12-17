package com.mynewsapp.mentor.di.module

import android.app.Application
import android.content.Context
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.repository.CountryRepository
import com.mynewsapp.mentor.data.repository.LanguageRepository
import com.mynewsapp.mentor.di.ApplicationContext
import com.mynewsapp.mentor.di.api.NetworkService
import com.mynewsapp.mentor.di.api.Networking
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: MyApplication){

    @ApplicationContext
    @Provides
    fun provideContext():Context{
        return application
    }

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        return Networking.create()
    }



}