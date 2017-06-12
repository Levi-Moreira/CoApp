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

    var connectionStatus: Boolean = false

    init {

        val connectedRef = database.child(".info/connected")
        connectedRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                connectionStatus = snapshot.getValue(Boolean::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                connectionStatus = false
            }
        })
    }

    fun getContactInfoFirebase() {

        val cInfoList = linkedMapOf<String, ContactInfo>()
        val contactInfoRef = database.child(ContactInfoFirebaseKey)

        val infoEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (connectionStatus) {
                    for (singleSnapshot in dataSnapshot.children) {
                        val value = singleSnapshot.getValue(ContactInfo::class.java)
                        val key = singleSnapshot.key
                        cInfoList[key] = value
                    }
                    presenter.retrievedInfo(cInfoList)
                } else {
                    presenter.connectionProblem()
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                presenter.failedToRetrieve()
            }
        }

        if(connectionStatus) {
            contactInfoRef.addListenerForSingleValueEvent(infoEventListener)
        }else{
            presenter.connectionProblem()
        }


    }


}