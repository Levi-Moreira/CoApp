package br.edu.ifce.lds.coapp.apiservice

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by levi on 03/07/17.
 */

class CoAppBackend() {
    val SERVER_BASE_URL = "http://coapplds.herokuapp.com"

    val API_PUBLIC_TOKEN = "3a77a2d614655ece007de7c4054b098db0f961c7"

    lateinit var backendAPI: BackendAPI

    val publicTokenFormatted
        get() = "Token " + API_PUBLIC_TOKEN


    init {
        val retrofit = Retrofit
                .Builder()
                .baseUrl(SERVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        backendAPI = retrofit.create(BackendAPI::class.java)
    }
}
