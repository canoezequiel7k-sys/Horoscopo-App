package com.cursokotlin.horoscapp.data.network

import com.cursokotlin.horoscapp.BuildConfig.BASE_URL
import com.cursokotlin.horoscapp.data.RepositoryImpl
import com.cursokotlin.horoscapp.data.core.interceptors.AuthInterceptor
import com.cursokotlin.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //si quiero que todos se pueda inyectarse a esto SingletonComponent::class
object NetworkModule {
    @Provides
    @Singleton  //patron de diseño que nos permite tener un unica instancia de de una clase
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        //esto tiene que devolver un objeto Retrofit en todos lados
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor )
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService{
        //creamos el provider con herencia para poder injectarlo
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository{
        //Yo cuando pido una interfaz, no deberia saber como se implementa, porque es parte de data
        return RepositoryImpl(apiService)
    }

}