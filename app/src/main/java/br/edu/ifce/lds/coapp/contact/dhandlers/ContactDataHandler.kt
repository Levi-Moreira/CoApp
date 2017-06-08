package br.edu.ifce.lds.coapp.contact.dhandlers

import br.edu.ifce.lds.coapp.contact.ContactPresenter
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.entities.ContactInfoFirebaseKey
import br.edu.ifce.lds.coapp.firebase.FirebaseCloud
import br.edu.ifce.lds.coapp.firebase.GetContactsInfoResponse
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable


/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler(val mPrefs: PreferencesUtil, val database: DatabaseReference, val presenter: ContactPresenter) {

    private val theCloud = FirebaseCloud()

    fun getContactInfo(): Observable<GetContactsInfoResponse> {
        return theCloud.apiService.getContactsInfo(mPrefs.getTokenFormatted())
    }

    fun getContactInfoFirebase() {

        val cInfoList = linkedMapOf<String, ContactInfo>()
        val contactInfoRef = database.child(ContactInfoFirebaseKey)



        contactInfoRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (singleSnapshot in dataSnapshot.children) {
                    val value = singleSnapshot.getValue(ContactInfo::class.java)
                    val key = singleSnapshot.key
                    cInfoList[key] = value
                }
                presenter.retrievedInfo(cInfoList)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })


    }


}