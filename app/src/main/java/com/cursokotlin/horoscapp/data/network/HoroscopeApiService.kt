package com.cursokotlin.horoscapp.data.network

import com.cursokotlin.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

//esto se tiene que llamar desde una clase
interface HoroscopeApiService {
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): PredictionResponse
}