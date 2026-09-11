package com.cursokotlin.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel
import com.cursokotlin.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase): ViewModel(){
//En el viewModel es donde se inyectan los casos de uso
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading) //Siempre hay que inicializarlo con un state
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope: HoroscopeModel

    fun getHoroscope(sign: HoroscopeModel){
        horoscope = sign
        //Para lanzar una coroutine en viewModel (El hilo que le asignamos, se lo asignamos all su contenido)
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            //hilo principal
            val result = withContext(Dispatchers.IO){getPredictionUseCase(sign.name.lowercase())} //Hilo secundario
            //hilo principal
            if (result != null){
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            }else{
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, intentelo mas tarde!")
            }
        }

    }


}