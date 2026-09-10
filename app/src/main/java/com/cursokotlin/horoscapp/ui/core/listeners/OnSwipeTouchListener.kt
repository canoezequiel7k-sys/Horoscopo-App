package com.cursokotlin.horoscapp.ui.core.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

//Esta clase va ser una clase abierta y va a necesitar un contexto
open class OnSwipeTouchListener(context: Context): View.OnTouchListener {

    private val gestureDetector: GestureDetector
    //Inicializamos el GestureDetector
    init {
        //Como el init es un constructor, va a pasar por aqui
        gestureDetector = GestureDetector(context, GestureListenes())
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}