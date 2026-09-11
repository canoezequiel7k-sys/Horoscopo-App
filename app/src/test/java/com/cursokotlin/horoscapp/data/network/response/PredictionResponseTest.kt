package com.cursokotlin.horoscapp.data.network.response

import com.cursokotlin.horoscapp.motherobject.HoroscopeMotherObject
import com.cursokotlin.horoscapp.motherobject.HoroscopeMotherObject.anyResponses
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test

class PredictionResponseTest {
    //El metodo toDomain deberian retornarno un predictionModel correcto
    @Test
    fun `toDomain Should Return Correct PredictionModel`(){
        //Given (Yo te doy la informacion necesaria para el test)
        val horoscopeResponse = anyResponses
        //Si en algun momento yo necesito que el sign sea libra, yo puedo hacer:
        //val horoscopeResponse = anyResponses.copy(sign = "libra")


        //When (Cuando se ejecuta y pase su accion)
        val predictionModel = horoscopeResponse.toDomain()


        //Then (Entonces verifico que ocurrio)
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
    }
}