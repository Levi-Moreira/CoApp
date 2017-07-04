package br.edu.ifce.lds.coapp.apiservice

import br.edu.ifce.lds.coapp.apiservice.retrofitmodels.GetContactsResponse
import br.edu.ifce.lds.coapp.contact.entities.Contact
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by levi on 03/07/17.
 */

interface BackendAPI {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/api/coworkings/{coworking_id}/contact_infos")
    fun getContacts(
            @Path("coworking_id") coworking_id: String,
            @Header("Authorization") auth_token: String): Observable<Response<ArrayList<ContactInfo>>>
}