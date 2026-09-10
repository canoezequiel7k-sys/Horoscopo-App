package com.cursokotlin.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//Da igual que sea un Interceptor, clase es clase, podes inyectarla para que sea injectable
class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Peticion que va a llegar al servidor. Vuelvete a crear sin perder tu informacion, pues quiero que le metas un header, ahora vuelvete a buildear
        val request = chain.request()
            .newBuilder()
            .header("Autorization", tokenManager.getToken()) //El value es la funcion que nosotros le mandamos
            .build() //Los header requiere clave valor

        //Vas a retornar la cadena, vas a continuar pero metiendo mi request
        return chain.proceed(request)  //No sirve de mucho si tenemos que darlr el valor a mano
    }
}

//No tiene que estar aca, es un ejemplo... Esta clase seria la que cuando haga Loggin, lo almacene en base de datos
class TokenManager @Inject constructor(){

    fun getToken(): String = "tokenEjemplo" //Esta funcion accederia a BDD y recuperaria el token
}