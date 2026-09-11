package com.cursokotlin.horoscapp.data.network.response

import com.cursokotlin.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

//Modelo de datos
data class PredictionResponse(
    @SerializedName("data") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
){
    //Convertimos el modelo de datos en otro
    fun toDomain(): PredictionModel{
        //vas a crear un Prediction Model y le vas a devolver el dato que te digo
        return PredictionModel(
            horoscope = horoscope,
            sign = sign)
    }
}
