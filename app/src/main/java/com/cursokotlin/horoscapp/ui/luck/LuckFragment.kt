package com.cursokotlin.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.databinding.ActivityMainBinding
import com.cursokotlin.horoscapp.databinding.FragmentLuckBinding
import com.cursokotlin.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {


    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!
    //Injectando una clase, esto me lo provee daggerHilt gracias al @AndroidEntryPoint
    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    //Aca LLamar a nuestro metodo inicial
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        //Vamos diciendole que vaya preparando la prediccion
        PreparePrediction()
        //Creo la funcion que escucha
        initListeners()
    }

    private fun PreparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        //All lo que haga aca adentro, me asegura que no es null, devuelve un luckyModel no nulable
        currentLuck?.let {luck ->
            //Guardamos el valor del.text
            val currentPrediction = getString(luck.text)
            //convierte el ID numérico en la frase traducida del @strings
            binding.tvLucky.text = currentPrediction
            //convierte el ID numérico en la frase traducida del @drawable
            binding.ivLuckyCard.setImageResource(luck.image)
            //Convertir nuestro tvShare en clickeable
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    private fun shareResult(prediction: String, ) {
        //vas a crear un valor tipo intent pero antes vas aplicarle unos atributos
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }
        //Chooser es el seleccionador, el apartado que te muestra a que app mandarlo
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    private fun initListeners() {
        //Hacemos el clickable y que llame a la funcion que gira la ruleta
        binding.ivRulete.setOnClickListener { spinRoulette() }
    }

    private fun spinRoulette() {
        //Esta funcion Random() nos permite generar numeros random
        val random = Random()
        //Cuantos grados quiero rotar(Le asignamos un numero maximo de vueltas) + 360 es que le asigno como minimo una vuelta
        val degress = random.nextInt(1440) + 360

        //Esto es un objeto animator, le pasamos un float(Serie de valores...) llamamos a la imagen, y le asignamos un ROTATION
        val animator = ObjectAnimator.ofFloat(binding.ivRulete, View.ROTATION, 0f, degress.toFloat())

        //Ahora al animator yo puedo ponerle lo que yo quiera
        animator.duration = 2000 //Le asigno una duracion de 2seg
        animator.interpolator = DecelerateInterpolator()  //Quiero que mi animacion empiece rapido y acabe lento
        animator.doOnEnd { slideCard() } //Cuando termine esta animacion, vas a llamar a esta funcion lambda
        animator.start() //Con esto deberia ejecutar
    }

    private fun slideCard(){
        //Android te provee un util de animaciones, cargame una animacion(Puede ser de hasta tipo xml)
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        //Meter un listener, definimos el objeto de tipo Animation y llamamos al AnimationListener
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            //Si quiero que haga algo cuando empiece
            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.isVisible = true
            }
            //Si quiero que haga algo cuando termine la animacion
            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            //Si quiero que haga algo cuando se vayya a repetir la animacion
            override fun onAnimationRepeat(p0: Animation?) {}

        })
        //Llamar al ivReverse e Iniciar la animacion, le pasamos el slideUpAnimation
        binding.ivReverse.startAnimation(slideUpAnimation)

    }


    private fun growCard() {
        //Le asignamos los Utils que viene con defecto en android y le asignamos nuestra animacion xml
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            //lo que hara cuando inicie la animacion
            override fun onAnimationStart(p0: Animation?) {}
            //lo que hara cuando termine
            override fun onAnimationEnd(p0: Animation?) {
                //Le estamos diciendo que cuando termine la visibilidad de la carta, se vuelva a gone
                binding.ivReverse.isVisible = false
                showPremonitionView()
            }
            //lo que hara cuando se repita
            override fun onAnimationRepeat(p0: Animation?) {}

        })
        binding.ivReverse.startAnimation(growAnimation)

    }

    //funcion para mostrar la nueva vista
    private fun showPremonitionView() {
        //Yo te voy a pasar algo que va a estar al 100% visible y lo vas a pasar al 0% Alpha cambia la opacidad
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        //La duracion de la animacion
        disappearAnimation.duration = 200

        //El contrario de la animacion, para que aparezca de a poco, este al 0% y pase al 100% de opacidad en 1 segundo
        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener{
            //Cuando empiece
            override fun onAnimationStart(p0: Animation?) {}
            //Cuando termine
            override fun onAnimationEnd(p0: Animation?) {
                //Cuando termine esta animacion, vas a agarrar el preview(el constraint con la ruleta y la carta en reversa) y vas a poner su visibilidad en gone
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }
            //Cuando se repita
            override fun onAnimationRepeat(p0: Animation?) {}

        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}