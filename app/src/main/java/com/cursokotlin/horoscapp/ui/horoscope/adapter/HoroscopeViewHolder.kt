package com.cursokotlin.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.horoscapp.databinding.ItemHoroscopoBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo


class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //
    private val binding = ItemHoroscopoBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvhoroscope.context

        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvhoroscope.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)}) //Creamos una nueva funcion lambda que va a llamar a nuestra funcion lambda
            //onItemSelected(horoscopeInfo)
        }
    }

    private fun startRotationAnimation(view: View, newLambda:()-> Unit){
        //animate pero antes vas aplicarte
        view.animate().apply{
            //Define la duracion, va en milisegindos 1000 = 1seg
            duration = 500
            //Defino el flujo de la animacion
            interpolator = LinearInterpolator() //tiene la misma animacion desde el principio hasta el fin
            rotationBy(360f) //rotar sobre su propio punto en 360 grados
            withEndAction { newLambda() } //el codigo de aca es un funcion lambda, se ejecuta cuando termine la animacion
            start()
        }
    }

}