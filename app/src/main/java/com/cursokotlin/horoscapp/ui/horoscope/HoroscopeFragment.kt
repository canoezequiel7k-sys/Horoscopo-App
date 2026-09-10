package com.cursokotlin.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.horoscapp.databinding.FragmentHoroscopeBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo
import com.cursokotlin.horoscapp.domain.model.HoroscopeInfo.*
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel
import com.cursokotlin.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private lateinit var HoroscopeAdapter: HoroscopeAdapter

    //enganchando hiltdeviewmodel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    //que se enganche con el viewModel

    //inicializamos el binding
    private var _binding: FragmentHoroscopeBinding? =
        null //cuando queremos acceder a variables privadas, le ponemos _

    //cuando yo llamo a binding, para acceder, me devuelve _binding PERO NO VA A SER NULO
    private val binding get() = _binding!!    //valor fijo, no se puede modificar ni romper, pero por detras llamar al var que es el que se podria modificar

    //Los metodos se llaman automaticamente cuando el activity se engancha a este fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        HoroscopeAdapter = HoroscopeAdapter(
            onItemSelected = {
                val type = when (it) {
                    Aquario -> HoroscopeModel.Aquarius
                    Aries -> HoroscopeModel.Aries
                    Cancer -> HoroscopeModel.Cancer
                    Crapicornio -> HoroscopeModel.Capricorn
                    Gemini -> HoroscopeModel.Gemini
                    Leo -> HoroscopeModel.Leo
                    Libra -> HoroscopeModel.Libra
                    Piscis -> HoroscopeModel.Pisces
                    Sagitario -> HoroscopeModel.Sagittarius
                    Scorpio -> HoroscopeModel.Scorpio
                    Tauro -> HoroscopeModel.Taurus
                    Virgo -> HoroscopeModel.Virgo
                }
                type

                //de esta manera navegamos a la pantalla de detalles
                findNavController().navigate(
                    HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
                )
            }
        )

        //agarra esta vista y aplica todos los atributos
        binding.rvHoroscope.apply {
            //va a ser un gridlayoutmanager
            layoutManager = GridLayoutManager(context, 2)
            adapter = HoroscopeAdapter
        }
        //binding.rvHoroscope.layoutManager = LinearLayoutManager(context)
        //binding.rvHoroscope.adapter = adapter
    }

    private fun initUIState() {
        //vincularnos al ViewModel con una corutine
        lifecycleScope.launch {
            //repite el ciclo de vida, cuando empiece el ciclo
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Agarramos nuestro viewmodel y enganchate
                horoscopeViewModel.horoscope.collect {
                    //cambio en horoscope
                    HoroscopeAdapter.updateList(it)
                    Log.i("Ari", it.toString())
                }
            }
        }
    }

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