package dev.ronnie.pokeapiandroidtask


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *created by Ronnie Otieno on 22-Dec-20.
 **/

@RunWith(AndroidJUnit4::class)
class PokemonStaFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun navigateToTheFragment() {

        //Wait for the pokemon to load, should fail in case pokemon list doesn't load.
        //alternatively pass the pokemon as bundle without relying on network call
        Thread.sleep(5000)

        val recyclerView = onView(
            allOf(
                withId(R.id.pokemon_list)
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @Test
    fun toolbar_is_showing(): Unit = runBlocking {
        onView(withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun recyclerview_is_showing() {
        onView(withId(R.id.pokemon_stat_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun card_is_showing() {
        onView(withId(R.id.card))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
