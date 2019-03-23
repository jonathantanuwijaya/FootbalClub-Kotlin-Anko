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
    fun onEachButtonNavigationClick(){
        onView(withId(R.id.menu_prev_match)).perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.menu_next_match)).perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.menu_favorites)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun onDetailsEvent(){
        val menu = arrayOf(R.id.menu_prev_match, R.id.menu_next_match, R.id.menu_favorites)

        for(m in menu){
            onView(withId(m)).perform(click())
            Thread.sleep(500)

            try{
                onView(withId(R.id.rv_match)).perform(
                    actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
                )
                Thread.sleep(500)
                Espresso.pressBack()
            }catch (e: Exception) {
                Log.e(MainActivity::class.java.simpleName, "Data kosong!")
            }
        }
    }

    @Test
    fun onAddRemoveMainEventToFavorite(){
        val menu = arrayOf(R.id.menu_prev_match, R.id.menu_next_match, R.id.menu_favorites)
        for(m in menu){
            onView(withId(m)).perform(click())
            Thread.sleep(500)

            try{
                onView(withId(R.id.rv_match)).perform(
                    actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
                )
                Thread.sleep(500)

                onView(withId(R.id.bt_favorite)).perform(click())
                Thread.sleep(100)
                Espresso.pressBack()
            }catch (e: Exception) {
                Log.e(MainActivity::class.java.simpleName, "Data kosong!")
            }
        }
    }

    @Test
    fun onAddResultSearchEventToFavorite() {
        val club = "Barchelona"
        Thread.sleep(1000)

        onView(withId(R.id.menu_search)).perform(click())
        onView(withId(R.id.et_finder)).perform(typeText(club))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(15)
        )
        Thread.sleep(500)

        onView(withId(R.id.rv_match)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        Thread.sleep(500)

        try{
            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
            onView(withId(R.id.bt_favorite)).perform(click())
            Thread.sleep(100)
        }catch (e: Exception){
            Log.e(MainActivity::class.java.simpleName, "Data kosong")
        }
    }

    @Test
    fun onSearchEventTest() {
        val clubs = arrayListOf("Barchelona", "Madrid", "Manchester", "Liverpool")
        onView(withId(R.id.menu_search)).perform(click())

        for(club in clubs){
            onView(withId(R.id.et_finder)).perform(typeText(club))
            Espresso.closeSoftKeyboard()
            Thread.sleep(1000)

            onView(withId(R.id.rv_match)).perform(
                scrollToPosition<RecyclerView.ViewHolder>(15)
            )
            Thread.sleep(500)

            onView(withId(R.id.rv_match)).perform(
                scrollToPosition<RecyclerView.ViewHolder>(0)
            )
            Thread.sleep(500)

            onView(withId(R.id.rv_match)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
            Thread.sleep(100)

            pressBack()
            Thread.sleep(500)
            onView(withId(R.id.et_finder)).perform(clearText())
        }
    }
}
