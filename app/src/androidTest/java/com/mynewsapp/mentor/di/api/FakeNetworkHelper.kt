package com.mynewsapp.mentor.di.api

import android.content.Context
import com.mynewsapp.mentor.di.ApplicationContext

class FakeNetworkHelper(@ApplicationContext val context: Context) {

    private var status: Boolean = true

    fun isNetworkConnected(): Boolean {

        return status
    }


    fun setStatus(status:Boolean){
        this.status=status
    }

}