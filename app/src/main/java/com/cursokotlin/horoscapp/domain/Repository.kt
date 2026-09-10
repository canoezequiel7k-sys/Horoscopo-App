package com.cursokotlin.horoscapp.domain

import com.cursokotlin.horoscapp.domain.model.PredictionModel

//Es la comunicacion la capa de data y la capa de dominio.
interface Repository{
    //voy a definir las cosas que necesito de data
    suspend fun getPrediction(sign: String): PredictionModel?
}
