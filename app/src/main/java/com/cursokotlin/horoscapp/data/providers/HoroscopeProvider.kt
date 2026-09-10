package com.cursokotlin.horoscapp.data.providers

import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject


class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo>{
        return listOf(
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
}