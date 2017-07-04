package br.edu.ifce.lds.coapp.contact.presenter

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.contact.dhandlers.ContactDataHandler
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.views.ContactView
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.database.FirebaseDatabase


/**
 * Created by ellca on 06/06/2017.
 */

class ContactPresenter(val prefs: PreferencesUtil, val mView: ContactView) : BasePresenter<ContactView> {

    val mDataHandler = ContactDataHandler(this)


    fun getContactInfo() {
        mView.showLoading()
        mDataHandler.getContactInfoFirebase()
    }

    fun retrievedInfo(contactInfoList: LinkedHashMap<String, ContactInfo>) {
        mView.hideLoading()
        mView.retrievedContactInfo(contactInfoList)
    }

    fun failedToRetrieve() {
        mView.hideLoading()
        mView.onError("Ocorreu um erro, por favor tente novamente.")
    }

    fun connectionProblem() {
        mView.hideLoading()
        mView.onError("Erro de conexão. Por favor, verfifique se você está conectado(a).")
    }

}