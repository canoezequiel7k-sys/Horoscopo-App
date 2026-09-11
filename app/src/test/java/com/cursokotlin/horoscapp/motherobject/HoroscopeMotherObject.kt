package com.cursokotlin.horoscapp.motherobject

import com.cursokotlin.horoscapp.data.network.response.PredictionResponse
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Aquario
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Aries
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Crapicornio
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Leo
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Libra
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Piscis
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Sagitario
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Tauro
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.Virgo

object HoroscopeMotherObject {
    //Respuesta de backend
    //como normal general, voy a usar todos los modelos del motherobject para centralizar la fuente de datos del testeo
    val anyResponses = PredictionResponse("date", "prediction", "taurus")

    val horoscopeInfoList = listOf(
        Aries,
        Tauro,
        Gemini,
        Aquario,
        Cancer,
        Scorpio,
        Crapicornio,
        Virgo,
        Sagitario,
        Piscis,
        Leo,
        Libra
    )
}