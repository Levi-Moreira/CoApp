package br.edu.ifce.lds.coapp.firebase

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import rx.Observable
import java.util.*

/**
 * Created by ellca on 06/06/2017.
 */

open interface FirebaseAPi {


    @GET("/api/v1/occupations")
    fun getContactsInfo(
            @Header("Authorization") auth_token: String
    ): Observable<GetContactsInfoResponse>

}
