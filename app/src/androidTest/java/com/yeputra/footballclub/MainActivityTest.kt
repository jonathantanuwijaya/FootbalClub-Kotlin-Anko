package com.yeputra.footballclub

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.yeputra.footballclub.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onEachButtonNavigationClickTest(){
        onView(withId(R.id.menu_standing)).perform(click())

        onView(withId(R.id.menu_match)).perform(click())

        onView(withId(R.id.menu_teams)).perform(click())

        onView(withId(R.id.menu_favorites)).perform(click())
    }

    @Test
    fun onDetailsEventAndAddFavoriteTest(){
        onView(withId(R.id.menu_match)).perform(click())
        try{
            onView(withId(R.id.viewpager)).perform(swipeLeft())
            onView(withId(R.id.viewpager)).perform(swipeRight())

            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
            onView(withId(R.id.bt_favorite)).perform(click())
            Espresso.pressBack()
        }catch (e: Exception) {
            Log.e(MainActivity::class.java.simpleName, "Data kosong!")
        }
    }

    @Test
    fun onDetailsTeamPlayerAndAddFavoriteTest(){
        onView(withId(R.id.menu_teams)).perform(click())
        Thread.sleep(1500)
        try{
            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )

            onView(withId(R.id.viewpager)).perform(swipeLeft())

            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )

            onView(withId(R.id.viewpager)).perform(swipeLeft())

            onView(withId(R.id.menu_favorite)).perform(click())
            Espresso.pressBack()
        }catch (e: Exception) {
            Log.e(MainActivity::class.java.simpleName, "Data kosong!")
        }
    }

    @Test
    fun onRemoveFavoriteTest(){
        onView(withId(R.id.menu_favorites)).perform(click())
        try{
            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
            onView(withId(R.id.bt_favorite)).perform(click())
            Espresso.pressBack()

            onView(withId(R.id.viewpager)).perform(swipeLeft())
            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
            onView(withId(R.id.menu_favorite)).perform(click())
            onView(withId(R.id.viewpager)).perform(swipeLeft())
            Espresso.pressBack()
        }catch (e: Exception) {
            Log.e(MainActivity::class.java.simpleName, "Data kosong!")
        }
    }

    @Test
    fun onSearchEventAndDetailAndAddFavoriteTest() {
        onView(withId(R.id.menu_match)).perform(click())
        onView(withId(R.id.menu_search)).perform(click())
        onView(withId(R.id.et_finder)).perform(typeText("arsenal"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1500)

        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(15)
        )
        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(0)
        )

        onView(withId(R.id.rv_match)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.bt_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.et_finder)).perform(clearText())
    }

    @Test
    fun onSearchTeamAndDetailAndAddFavoriteTest() {
        onView(withId(R.id.menu_teams)).perform(click())
        onView(withId(R.id.menu_search)).perform(click())
        onView(withId(R.id.et_finder)).perform(typeText("arsenal"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1500)

        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(5)
        )

        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(0)
        )

        onView(withId(R.id.rv_match)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.rv_match)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.viewpager)).perform(swipeRight())
        onView(withId(R.id.menu_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.et_finder)).perform(clearText())
    }
}
