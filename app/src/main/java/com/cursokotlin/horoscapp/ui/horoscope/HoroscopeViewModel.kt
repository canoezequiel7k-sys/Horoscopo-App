package com.cursokotlin.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.cursokotlin.horoscapp.data.providers.HoroscopeProvider
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel //Desde el init o desde variables, no es necesario crearlo como una variable
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider): ViewModel() {

    //Creando un MUTABLE stateFlow, hay que inicializarlo en una lista vacia. Se crea privada porque no se tiene que modificar desde fuera
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    //Creo un valor fijo, para que el usuario pueda leer los datos de _horoscope pero no puede modificarlo
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

}