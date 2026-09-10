package com.cursokotlin.horoscapp.data

import android.util.Log
import com.cursokotlin.horoscapp.data.network.HoroscopeApiService
import com.cursokotlin.horoscapp.data.network.response.PredictionResponse
import com.cursokotlin.horoscapp.domain.Repository
import com.cursokotlin.horoscapp.domain.model.PredictionModel
import retrofit2.Retrofit
import javax.inject.Inject

//va a extender de Repository del domain (Desde este repositorio ya puedo hacer peticiones a internet
class RepositoryImpl@Inject constructor(private val apiService: HoroscopeApiService): Repository {
    //me obliga a meter la funcion getPrediction,
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() } //ejecuta este codigo si corrio bien
            .onFailure { Log.i("Aris", "Ha ocurrido un error ${it.message}") } //ejecuta este codigo si corrio mal

        return null //Si no retorna nada es null
    }
}