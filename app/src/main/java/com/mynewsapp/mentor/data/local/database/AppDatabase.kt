package com.mynewsapp.mentor.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mynewsapp.mentor.data.local.dao.TopHeadlinesDao
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import kotlinx.coroutines.flow.Flow

@Database(entities = [TopHeadlines::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topHeadlinesDao(): TopHeadlinesDao

}