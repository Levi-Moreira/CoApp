package br.edu.ifce.lds.coapp.contact.dhandlers

import android.util.Log
import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.entities.ContactInfoFirebaseKey
import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler(val database: DatabaseReference, val presenter: ContactPresenter) {

    val TAG = "ContactDataHandler"

    val backend = CoAppBackend()


    fun getContactInfoFirebase() {


        backend
                .backendAPI
                .getContacts("1", backend.publicTokenFormatted)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            contacts ->

                            if (contacts.isSuccessful) {
                                if (contacts.body() != null) {
                                    val cInfoList = linkedMapOf<String, ContactInfo>()
                                    for (info in contacts.body()!!) {
                                        cInfoList[info.id.toString()] = info
                                    }
                                    presenter.retrievedInfo(cInfoList)
                                }
                            } else {
                                presenter.failedToRetrieve()
                            }

                        },
                        {
                            error ->
                            presenter.failedToRetrieve()
                        }
                )

    }


}