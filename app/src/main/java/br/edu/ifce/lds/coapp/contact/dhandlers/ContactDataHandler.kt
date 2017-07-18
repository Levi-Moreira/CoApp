package br.edu.ifce.lds.coapp.contact.dhandlers

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler @Inject constructor() : IContactDataHandler {

    val TAG = "ContactDataHandler"

    val backend = CoAppBackend()

    override fun getContactInfo(): Observable<Response<ArrayList<ContactInfo>>> = backend.backendAPI.getContacts("1", backend.publicTokenFormatted)


}