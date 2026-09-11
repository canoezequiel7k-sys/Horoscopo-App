package com.cursokotlin.horoscapp.ui.horoscope

import com.cursokotlin.horoscapp.data.providers.HoroscopeProvider
import com.cursokotlin.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

//Antes de realizar los test y despues de realizar los test, podemos realizar ciertas operaciones
class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider  //Con esto mockk nos permite crear ese objeto falso
    private lateinit var viewModel: HoroscopeViewModel

    //Con esto conseguimos que antes de ejecutarse haga lo de Before, esto se utiliza para cuando tengamos logica compartida
    @Before
    fun setUp() {
        //
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    //Necesito que a penas cargue una pantalla, siempre cargue un horoscopo
    //Cuando se cree este viewmodel, vas a tener automaticamente que recuperar todos los horoscopos y mostrarlos
    @Test
    fun `when viewmodel is created then horoscope are loaded`(){
        //GIVEN
        //Cada vez que alguien llame al horoscopeProvider,getHoroscope(), retornale es la respuesta que yo le de
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        //Si esta funcion usase Corutines
        //coEvery { horoscopeProvider.getHoroscopes() } returns listOf()
        viewModel = HoroscopeViewModel(horoscopeProvider)

        //WHEN
        //cuando horoscopes es igual a los valores de horoscope del viewmodel
        val horoscopes = viewModel.horoscope.value

        //THEN
        //Entonces comprueba que el valor de mi horoscopes no esta vacio
        assertTrue(horoscopes.isNotEmpty())
    }




}