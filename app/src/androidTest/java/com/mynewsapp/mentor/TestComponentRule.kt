package com.mynewsapp.mentor

import android.content.Context
import com.mynewsapp.mentor.di.component.DaggerTestComponent
import com.mynewsapp.mentor.di.component.TestComponent
import com.mynewsapp.mentor.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {

    var testComponent: TestComponent? = null

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as MyApplication

        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()

        application.setTestComponent(testComponent!!)
    }

    override fun apply(base: Statement, description: Description): Statement {

        return object : Statement() {
            @Throws(Throwable::class)

            override fun evaluate() {

                try {

                    setupDaggerTestComponentInApplication()
                    base.evaluate()
                } finally {

                    testComponent = null

                }

            }

        }

    }
}