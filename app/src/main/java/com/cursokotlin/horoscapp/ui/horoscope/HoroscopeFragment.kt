package com.cursokotlin.horoscapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cursokotlin.horoscapp.databinding.FragmentHoroscopeBinding


class HoroscopeFragment : Fragment() {
    //inicializamos el binding
    private var _binding: FragmentHoroscopeBinding? =
        null //cuando queremos acceder a variables privadas, le ponemos _

    //cuando yo llamo a binding, para acceder, me devuelve _binding PERO NO VA A SER NULO
    private val binding get() = _binding!!    //valor fijo, no se puede modificar ni romper, pero por detras llamar al var que es el que se podria modificar

    //Los metodos se llaman automaticamente cuando el activity se engancha a este fragment


    //Nuestro metodo principal, se llama automaticamente
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        //este crea la vista del Fragment
        return binding.root
    }

}