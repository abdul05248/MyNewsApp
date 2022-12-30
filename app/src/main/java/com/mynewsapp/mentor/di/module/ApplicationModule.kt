package com.mynewsapp.mentor.di.module

import android.content.Context
import androidx.room.Room
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.local.database.AppDatabase
import com.mynewsapp.mentor.data.local.database.DatabaseHelperImpl
import com.mynewsapp.mentor.di.ApplicationContext
import com.mynewsapp.mentor.di.BaseUrl
import com.mynewsapp.mentor.di.DatabaseName
import com.mynewsapp.mentor.di.api.ApplicationInterceptor
import com.mynewsapp.mentor.di.api.NetworkHelper
import com.mynewsapp.mentor.di.api.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://newsapi.org/v2/"


    @DatabaseName
    @Provides
    fun provideDatabaseName(): String="news_app_offline"

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: ApplicationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(@BaseUrl baseUrl: String, gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient):

            NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)//Ye inject nahi kr rhe hain
            .build()
            .create(NetworkService::class.java)
    }


    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context)= NetworkHelper(context)

    @Provides
    fun provideDatabaseHelperImp(appDatabase: AppDatabase)= DatabaseHelperImpl(appDatabase)

    //Ask$ I don't understand
    //Why passing value when nothing is in AppDatabase
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context,@DatabaseName name:String): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        name
    ).build()



}