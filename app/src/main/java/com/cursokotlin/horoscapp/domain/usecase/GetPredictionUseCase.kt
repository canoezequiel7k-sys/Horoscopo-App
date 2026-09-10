package com.cursokotlin.horoscapp.domain.usecase

import com.cursokotlin.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository){

    //Creo el caso de uso
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)

}