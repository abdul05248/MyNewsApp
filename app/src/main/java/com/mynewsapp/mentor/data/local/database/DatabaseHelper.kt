package com.mynewsapp.mentor.data.local.database

import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

fun getTopHeadlines():Flow<List<TopHeadlines>>

fun deleteAll():Int

fun insertAll( data: List<TopHeadlines>):Flow<Unit>

}