package br.edu.ifce.lds.coapp.contact.presenter

import br.edu.ifce.lds.coapp.contact.dhandlers.ContactDataHandler
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.views.ContactView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by ellca on 06/06/2017.
 */

class ContactPresenter @Inject constructor(val mView: ContactView) : IContactPresenter {


    @Inject
    lateinit var mDataHandler: ContactDataHandler


    override fun getContactInfo() {
        mView.showLoading()
        mDataHandler.getContactInfo()
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
                                    retrievedInfo(cInfoList)
                                }
                            } else {
                                failedToRetrieve()
                            }

                        },
                        {
                            error ->
                            failedToRetrieve()
                        }
                )
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