package com.example.jetpackcomposetutorial.api

import android.media.Image
import com.example.jetpackcomposetutorial.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"
    private const val API_KEY = BuildConfig.CAT_API_KEY

    //Configures our http client to automatically add the api key to each request
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .apply{
                addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .header("x-api-key", API_KEY)
                            .build()
                    )
                }
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val breedService: BreedService by lazy {
        retrofit.create(BreedService::class.java)
    }

    val imageService: ImageService by lazy {
        retrofit.create(ImageService::class.java)
    }
}