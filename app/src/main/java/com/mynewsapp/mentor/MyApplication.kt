package com.mynewsapp.mentor

import android.app.Application
import com.mynewsapp.mentor.di.component.ApplicationComponent
import com.mynewsapp.mentor.di.component.DaggerApplicationComponent
import com.mynewsapp.mentor.di.module.ApplicationModule

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        injectDependencies()

    }

    private fun injectDependencies() {

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

    }
    // Needed to replace the component with a test specific one
    fun setTestComponent(applicationComponent: ApplicationComponent){
        this.applicationComponent = applicationComponent
    }
}