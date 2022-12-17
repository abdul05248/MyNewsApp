package com.mynewsapp.mentor.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mynewsapp.mentor.data.repository.CountryRepository
import com.mynewsapp.mentor.data.repository.LanguageRepository
import com.mynewsapp.mentor.data.repository.NewsSourceRepository
import com.mynewsapp.mentor.data.repository.TopHeadlinesRepository
import com.mynewsapp.mentor.di.ActivityContext
import com.mynewsapp.mentor.di.ActivityScope
import com.mynewsapp.mentor.di.api.NetworkService
import com.mynewsapp.mentor.ui.base.ViewModelProviderFactory
import com.mynewsapp.mentor.ui.countries.CountryAdapter
import com.mynewsapp.mentor.ui.countries.CountryViewModel
import com.mynewsapp.mentor.ui.languages.LanguageAdapter
import com.mynewsapp.mentor.ui.languages.LanguageViewModel
import com.mynewsapp.mentor.ui.sources.NewsSourceAdapter
import com.mynewsapp.mentor.ui.sources.NewsSourceViewModel
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesAdapter
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlinesRepository): TopHeadlinesViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlinesViewModel::class) {
            TopHeadlinesViewModel(topHeadlineRepository)
        })[TopHeadlinesViewModel::class.java]
    }

  /*  @Provides
    fun provideNewsListViewModels(topHeadlineRepository: TopHeadlinesRepository): TopHeadlinesViewModel {
        val viewModelFactory= ViewModelProviderFactory(TopHeadlinesViewModel::class, {
            TopHeadlinesViewModel(topHeadlineRepository)
        })

        return ViewModelProvider(activity, viewModelFactory)[TopHeadlinesViewModel::class.java]
    }*/

    @Provides
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourceRepository): NewsSourceViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(NewsSourceViewModel::class) {
            NewsSourceViewModel(newsSourceRepository)
        })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideCountryViewModel(countryRepository: CountryRepository):CountryViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(CountryViewModel::class){
            CountryViewModel(countryRepository)
        })[CountryViewModel::class.java]
    }

    @Provides
    fun provideLanguageViewModel(languageRepository: LanguageRepository):LanguageViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(LanguageViewModel::class){
            LanguageViewModel(languageRepository)
        })[LanguageViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlinesAdapter() =TopHeadlinesAdapter(ArrayList())

    @Provides
    fun provideNewsSourceAdapter() =NewsSourceAdapter(ArrayList())

    @Provides
    fun provideCountryAdapter() = CountryAdapter(ArrayList())

    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())

    @Provides
    fun provideCountryRepository() = CountryRepository()

    @Provides
    fun provideLanguageRepository() = LanguageRepository()

    @Provides
    fun provideTopHeadlinesRepository(networkService: NetworkService) = TopHeadlinesRepository(networkService)

    @Provides
    fun getNewsSourceRepository(networkService: NetworkService)= NewsSourceRepository(networkService)
}