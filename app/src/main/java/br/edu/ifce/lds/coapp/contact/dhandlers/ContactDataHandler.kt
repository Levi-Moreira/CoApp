package br.edu.ifce.lds.coapp.contact.dhandlers

import android.widget.Toast
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.firebase.FirebaseCloud
import br.edu.ifce.lds.coapp.firebase.GetContactsInfoResponse
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.auth.AuthResult
import io.reactivex.Observable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.internal.util.HalfSerializer.onComplete


/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler(val mPrefs: PreferencesUtil) {

    private val theCloud = FirebaseCloud()
    private val mAuth = FirebaseAuth.getInstance()

    init {

    }


    fun getContactInfo(): Observable<GetContactsInfoResponse> {
        return theCloud.apiService.getContactsInfo(mPrefs.getTokenFormatted())
    }


}