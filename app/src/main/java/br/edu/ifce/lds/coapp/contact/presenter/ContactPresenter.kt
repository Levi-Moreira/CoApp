package br.edu.ifce.lds.coapp.contact.presenter

import br.edu.ifce.lds.coapp.contact.dhandlers.ContactDataHandler
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.views.ContactView
import javax.inject.Inject


/**
 * Created by ellca on 06/06/2017.
 */

class ContactPresenter @Inject constructor(val mView: ContactView) : IContactPresenter {


    val mDataHandler = ContactDataHandler(this)


    override fun getContactInfo() {
        mView.showLoading()
        mDataHandler.getContactInfoFirebase()
    }

    override fun retrievedInfo(contactInfoList: LinkedHashMap<String, ContactInfo>) {
        mView.hideLoading()
        mView.retrievedContactInfo(contactInfoList)
    }

    override fun failedToRetrieve() {
        mView.hideLoading()
        mView.onError("Ocorreu um erro, por favor tente novamente.")
    }

    override fun connectionProblem() {
        mView.hideLoading()
        mView.onError("Erro de conexão. Por favor, verfifique se você está conectado(a).")
    }


}