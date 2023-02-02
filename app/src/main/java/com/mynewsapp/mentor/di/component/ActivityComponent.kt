package com.mynewsapp.mentor.di.component

import android.app.Activity
import com.mynewsapp.mentor.di.ActivityScope
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.countries.CountryActivity
import com.mynewsapp.mentor.ui.languages.LanguageActivity
import com.mynewsapp.mentor.ui.languages.LanguageNewsActivity
import com.mynewsapp.mentor.ui.search.SearchActivity
import com.mynewsapp.mentor.ui.sources.NewsSourceActivity
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlinesActivity)

    fun inject(activity: NewsSourceActivity)

    fun inject(activity: CountryActivity)

    fun inject(activity: LanguageActivity)

    fun inject(activity: SearchActivity)

    fun inject(activity: LanguageNewsActivity)


}