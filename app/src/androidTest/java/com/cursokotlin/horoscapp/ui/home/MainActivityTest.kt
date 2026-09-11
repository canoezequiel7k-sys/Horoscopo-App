package com.cursokotlin.horoscapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.ui.detail.HoroscopeDetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) //Le digo que tiene que correr estos test con AndroidJUnit4
@HiltAndroidTest //Porque va a esperar un HiltAndroidTest
class MainActivityTest {
    //Nosotros podemos crear Reglas (configuraciones que hay que meter siempre en la pantalla) hay que ponerlo en Rule
    @get:Rule(order = 0) //si usas dagger hilt, la primera de toda tiene que ser daggerhilt
    val hiltRule = HiltAndroidRule(this) //Es como preparar para que esta clase pueda ser inyectada

    //Para que haga testing de la clase MainActivity
    @get:Rule(order = 1) //No pueden ir con el mismo orden, tiene que ir sumandole 1
    var mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        hiltRule.inject()
        Intents.init()
    }
    //Si hay un Before, hay un After
    @After
    fun tearDown(){
        //Para que se liberen los intent, y no queden todos mezclados
        Intents.release()
    }


    @Test
    fun when_mainactivity_is_created_then_open_luck_fragment(){
        //Yo quiero que cuando lance mi main activity, vaya a la bottom bar y seleccione el fragment de luck. Y vas hacer una accion, click sobre esa id
        onView(withId(R.id.luckFragment)).perform(click()) //Buscame con la id, quien tenga esta id(R.id.luckFragment) RECUERDA QUE ESTO DEVUELVE UN INT
    }

    //Cuando yo en el recyvlearview pulse en un item real, comprueba que estas abriendo el detalle del activity
    @Test
    fun when_horoscope_is_selected_then_open_detail(){
        //Cuando estes en el rvView, haz una permorfa, haz una accion en un item posicionado del rv, agarras el item de la posicion 0 y vas hacer un click
        onView(withId(R.id.rvHoroscope)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        // Esperar a que la animación de 500ms del ViewHolder termine y ejecute el withEndAction
        Thread.sleep(600)

        //Comprueba un itent que tiene un componente HoroscopeDetailActivity, para que funcione tenemos que preparar los intent en este activity
        intended(hasComponent(HoroscopeDetailActivity::class.java.name))
    }
}