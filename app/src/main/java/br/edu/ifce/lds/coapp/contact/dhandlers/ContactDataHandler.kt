package br.edu.ifce.lds.coapp.contact.dhandlers

import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.entities.ContactInfoFirebaseKey
import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ServerValue
import java.util.concurrent.TimeoutException


/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler(val database: DatabaseReference, val presenter: ContactPresenter) {


    fun getContactInfoFirebase() {

        val cInfoList = linkedMapOf<String, ContactInfo>()
        val contactInfoRef = database.child(ContactInfoFirebaseKey)

        val infoEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (singleSnapshot in dataSnapshot.children) {
                    val value = singleSnapshot.getValue(ContactInfo::class.java)
                    val key = singleSnapshot.key
                    cInfoList[key] = value
                }
                presenter.retrievedInfo(cInfoList)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                presenter.failedToRetrieve()
            }
        }


        contactInfoRef.addListenerForSingleValueEvent(infoEventListener)


    }


}