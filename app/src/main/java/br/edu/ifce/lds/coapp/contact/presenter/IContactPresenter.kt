package br.edu.ifce.lds.coapp.contact.presenter

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.views.ContactView

/**
 * Created by lds on 7/18/17.
 */
interface IContactPresenter : BasePresenter<ContactView> {
    fun getContactInfo()
    fun retrievedInfo(contactInfoList: LinkedHashMap<String, ContactInfo>)
    fun failedToRetrieve()
    fun connectionProblem()
}