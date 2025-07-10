package com.pedromg.facultaddeciencias.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestTemplate {

    private const val BASE_URL = "https://facultaddecienciasuabc.net/public/api/"

    val newsClient: NewsClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(NewsClient::class.java)
    }

}