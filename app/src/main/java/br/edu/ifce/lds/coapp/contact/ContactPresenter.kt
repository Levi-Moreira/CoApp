package br.edu.ifce.lds.coapp.contact

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.contact.dhandlers.ContactDataHandler
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by ellca on 06/06/2017.
 */

class ContactPresenter(val prefs: PreferencesUtil, val mView: ContactView) : BasePresenter<ContactView> {

    val mDataHandler = ContactDataHandler(prefs)


    fun getContactInfo() {
        mView.showLoading()
        mDataHandler
                .getContactInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ info ->
                    //on completed
                    mView.hideLoading()
                    mView.retrievedContactInfo(info.contact_info)
                }, { onError ->
                    //on error
                    mView.hideLoading()
                    mView.onError(onError.message)
                },{

                })


    }

}