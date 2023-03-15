package com.mynewsapp.mentor.di.component

import com.mynewsapp.mentor.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {

}