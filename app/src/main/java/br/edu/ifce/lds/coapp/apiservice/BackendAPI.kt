package br.edu.ifce.lds.coapp.apiservice

import br.edu.ifce.lds.coapp.apiservice.retrofitmodels.GetContactsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * Created by levi on 03/07/17.
 */

interface BackendAPI {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/api/v1/users/{user_id}")
    fun getContacts(
            @Header("Authorization") auth_token: String): Observable<Response<GetContactsResponse>>
}