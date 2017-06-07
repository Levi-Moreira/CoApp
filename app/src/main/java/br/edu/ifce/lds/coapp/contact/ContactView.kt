package br.edu.ifce.lds.coapp.contact

import br.edu.ifce.lds.coapp.common.BaseView
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo

/**
 * Created by ellca on 06/06/2017.
 */

open interface ContactView : BaseView {
    fun retrievedContactInfo(contactsInfo: List<ContactInfo>)

    fun onError(message: String?)

    fun showLoading()

    fun hideLoading()

}
