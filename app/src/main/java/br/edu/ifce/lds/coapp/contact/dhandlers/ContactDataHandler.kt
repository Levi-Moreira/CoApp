package br.edu.ifce.lds.coapp.contact.dhandlers

import android.content.Context
import br.edu.ifce.lds.coapp.contact.ContactPresenter
import br.edu.ifce.lds.coapp.firebase.FirebaseCloud
import br.edu.ifce.lds.coapp.firebase.GetContactsInfoResponse
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import rx.Observable

/**
 * Created by ellca on 06/06/2017.
 */

class ContactDataHandler(val mPresenter: ContactPresenter, val mPrefs: PreferencesUtil) {

    val theCloud = FirebaseCloud()

    fun getContactInfo(): Observable<GetContactsInfoResponse> {
        return theCloud.apiService.getContactsInfo(mPrefs.getTokenFormatted())
    }


}