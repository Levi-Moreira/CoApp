package br.edu.ifce.lds.coapp.apiservice

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by levi on 03/07/17.
 * Encapsulates the access to the Retrofit backend
 */

class CoAppBackend() {
    //base URL where the server lives
    val SERVER_BASE_URL = "http://cohabitat.com.br/api/v1/"

    //master token
    private val API_PUBLIC_TOKEN = "3795120644e36ad156daac35a989db2dad78e154"

    var backendAPI: BackendAPI

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
