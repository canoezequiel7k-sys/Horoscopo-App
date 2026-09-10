package com.cursokotlin.horoscapp.ui.detail

import com.cursokotlin.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    //cuando es un estado sencillo que no requiere parametros, es un data object
    data object Loading: HoroscopeDetailState()
    //cuando hay que pasarle parametros, es una data class
    data class Error(val error: String): HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
}