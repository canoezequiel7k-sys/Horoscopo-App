package com.cursokotlin.horoscapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cursokotlin.horoscapp.R
import com.cursokotlin.horoscapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //preparamos el binding
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializamos viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets }

        initUI()
    }

    //Este metodo llamaria a pequeños metodos
    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment  //el AS lo castea(lo convierte en un fragment)
        //nuestro controlador, va a controlar nuestro fragmentContainerView
        navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)
    }
}