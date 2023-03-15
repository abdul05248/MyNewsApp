package com.mynewsapp.mentor.ui.topHeadlines

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.mynewsapp.mentor.R
import com.mynewsapp.mentor.TestComponentRule
import com.mynewsapp.mentor.utils.RVMatcher.atPositionOnView
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class TopHeadlinesActivityTest {

    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    @get:Rule
    val chain = RuleChain.outerRule(component)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TopHeadlinesActivity::class.java)

    @Before
    fun intentsInit() {

        Intents.init()
    }

    @After
    fun intentsTearDown() {

        Intents.release()
    }

    @Test
    fun topHeadlinesShouldDisplay() {

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(
            matches(atPositionOnView(1, withText("title2"), R.id.textViewTitle))
        )

        onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(4)).check(
            matches(atPositionOnView(4, withText("title3"), R.id.textViewTitle))
        )
    }

}