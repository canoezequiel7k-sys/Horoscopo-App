package com.cursokotlin.horoscapp.ui.providers

import org.junit.Assert.*
import org.junit.Test

class RandomCardProviderTest {

    //Test de que cuando me devuelva algo, no sea nulo
    @Test
    fun `getRandomCard should return a random card`(){
        //given (Seteo en mi val, los valores que puede tener)
        val randomCard = RandomCardProvider() //Lo que estoy testeando es la clase de verdad

        //when (Cuando yo llame a getLucky())
        val card = randomCard.getLucky() //llamo a mi funcion getLucky()

        //then (Entonces asegurate de que esto no sea nulo)
        assertNotNull(card)

    }
}