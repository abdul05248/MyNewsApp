package com.mynewsapp.mentor.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mynewsapp.mentor.data.local.database.AppDatabase
import com.mynewsapp.mentor.data.local.database.DatabaseHelperImpl
import com.mynewsapp.mentor.data.repository.*
import com.mynewsapp.mentor.di.ActivityContext
import com.mynewsapp.mentor.di.ApplicationContext
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.di.api.NetworkService
import com.mynewsapp.mentor.ui.base.ViewModelProviderFactory
import com.mynewsapp.mentor.ui.countries.CountryAdapter
import com.mynewsapp.mentor.ui.countries.CountryViewModel
import com.mynewsapp.mentor.ui.languages.LanguageAdapter
import com.mynewsapp.mentor.ui.languages.LanguageNewsAdapter
import com.mynewsapp.mentor.ui.languages.LanguageNewsViewModel
import com.mynewsapp.mentor.ui.languages.LanguageViewModel
import com.mynewsapp.mentor.ui.search.SearchPagingAdapter
import com.mynewsapp.mentor.ui.search.SearchViewModel
import com.mynewsapp.mentor.ui.sources.NewsSourceAdapter
import com.mynewsapp.mentor.ui.sources.NewsSourceViewModel
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesAdapter
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesViewModel
import com.mynewsapp.mentor.utils.DispatcherProvider
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
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlinesRepository, networkHelper: NetworkHelper,
    dispatcherProvider: DispatcherProvider):
            TopHeadlinesViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlinesViewModel::class) {
            TopHeadlinesViewModel(topHeadlineRepository, networkHelper, dispatcherProvider)
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
    fun provideSearchViewModel(searchRepository: SearchRepository):SearchViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(SearchViewModel::class){
            SearchViewModel(searchRepository)
        })[SearchViewModel::class.java]
    }

    @Provides
    fun provideLanguageNewsViewModel(languageNewsRepository: LanguageNewsRepository):LanguageNewsViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(LanguageNewsViewModel::class){
            LanguageNewsViewModel(languageNewsRepository)
        })[LanguageNewsViewModel::class.java]
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
    fun provideLanguageNewssAdapter() = LanguageNewsAdapter(ArrayList())

    @Provides
    fun provideSearchPagingAdapter() = SearchPagingAdapter()

    @Provides
    fun provideCountryRepository() = CountryRepository()

    @Provides
    fun provideLanguageRepository() = LanguageRepository()

    @Provides
    fun provideTopHeadlinesRepository(networkService: NetworkService, databaseHelperImpl: DatabaseHelperImpl) = TopHeadlinesRepository(networkService, databaseHelperImpl)

    @Provides
    fun getNewsSourceRepository(networkService: NetworkService)= NewsSourceRepository(networkService)

    @Provides
    fun getSearchRepository(networkService: NetworkService)= SearchRepository(networkService)

    @Provides
    fun getLanguageNewsRepository(networkService: NetworkService)= LanguageNewsRepository(networkService)

    //Ask$ yhan get se h and application me provide se h ?
}