package com.cursokotlin.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LuckyModel(
    //obligamos a que este valor sea una referencia de un Drawable
    @DrawableRes val image:Int,
    //obligamos a que este valor sea una referencia de un String
    @StringRes val text:Int
)