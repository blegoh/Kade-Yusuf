package me.yusufeka.kadeyusuf

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import me.yusufeka.kadeyusuf.views.MainActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testAppBehaviour() {
        onView(withId(R.id.rv))
            .check(matches(isDisplayed()))

        onView(withText("English Premier League"))
            .check(matches(isDisplayed()))

        onView(withText("English Premier League")).perform(click())

        onView(withId(R.id.action_search)).perform(click())

        onView(withId(R.id.search_src_text))
            .perform(replaceText("afasf"))
            .perform(pressImeActionButton())

        onView(withText("Data tidak ditemukan"))
            .inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(matches(isDisplayed()))

        onView(withId(R.id.search_src_text))
            .perform(replaceText("Liverpool"))
            .perform(pressImeActionButton())

        onView(withId(R.id.rvMatch)).check(RecyclerViewNotEmptyAssertion())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }
}