package br.edu.ifce.lds.coapp.firebase

import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


/**
 * Created by ellca on 06/06/2017.
 */

open interface FirebaseAPi {


    @GET("/.json")
    fun getContactsInfo(
            @Query("auth") auth_token: String
    ): Observable<GetContactsInfoResponse>

}
