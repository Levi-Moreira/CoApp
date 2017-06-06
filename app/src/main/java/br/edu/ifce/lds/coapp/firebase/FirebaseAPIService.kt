package br.edu.ifce.lds.coapp.firebase

import com.google.firebase.database.FirebaseDatabase
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by ellca on 06/06/2017.
 */

class FirebaseCloud(){
    //the main api url
    val SERVER_BASE_URL = "https://coapp-82041.firebaseio.com/"

    var apiService : FirebaseAPi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        apiService = retrofit.create<FirebaseAPi>(FirebaseAPi::class.java)
    }

    /**
     * Gets the base server address
     * @return the address of the base server
     */
    fun getServer_base_url(): String {
        return SERVER_BASE_URL
    }

}
