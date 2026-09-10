package com.cursokotlin.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel
import com.cursokotlin.horoscapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    //Recuperar argumento type
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets }
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initListener() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            //Repite en el ciclo de vida
            repeatOnLifecycle(Lifecycle.State.STARTED){
                //Se engancha al viewmodel de nuestro horoscope detail y lo collect
                horoscopeDetailViewModel.state.collect {
                    //Esta linea se va a llamar, siempre que se cambie el estado
                    when(it){
                        //adentro de las llaves, podemos hacer lo que queramos mientras entre a ese estado
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> {
                            //No es necesario poner llaves, las llaves son si es que son varias lineas de codigo
                            //a nuestro progressBar, lo ponemos en visible(Es mejor hacerlo directamente en funcion)
                            //binding.pbBody.isVisible = true
                            loadingState()
                        }
                        is HoroscopeDetailState.Success -> successState(it)
                        }
                    }
                }
            }
        }

    private fun loadingState() {
        binding.pbBody.isVisible = true
    }

    private fun errorState(){
        binding.pbBody.isVisible = false
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pbBody.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Aquarius -> R.drawable.detail_aquarius
            Cancer -> R.drawable.detail_cancer
            Capricorn -> R.drawable.detail_capricorn
            Scorpio -> R.drawable.detail_scorpio
            Gemini -> R.drawable.detail_gemini
            Leo -> R.drawable.detail_leo
            Libra -> R.drawable.detail_libra
            Pisces -> R.drawable.detail_pisces
            Sagittarius -> R.drawable.detail_sagittarius
            Virgo -> R.drawable.detail_virgo
        }

        binding.ivDetail.setImageResource(image)

    }
}
