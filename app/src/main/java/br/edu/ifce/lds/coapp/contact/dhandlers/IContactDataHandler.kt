package br.edu.ifce.lds.coapp.contact.dhandlers

import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by lds on 7/18/17.
 */
interface IContactDataHandler{
    fun getContactInfo(): Observable<Response<ArrayList<ContactInfo>>>
}