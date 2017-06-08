package br.edu.ifce.lds.coapp.contact

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.Slide
import android.view.View.GONE
import android.view.View.VISIBLE
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_contact.*
import android.widget.*
import br.edu.ifce.lds.coapp.contact.entities.ContactType
import br.edu.ifce.lds.coapp.utils.listWithNames
import org.jetbrains.anko.find
import org.jetbrains.anko.onCheckedChange


class ContactActivity : BaseActivity(), ContactView {

    lateinit var mPresenter: ContactPresenter

    lateinit var mContactInfo: LinkedHashMap<String, ContactInfo>

    var mContactPhones = mutableListOf<ContactInfo>()
    var mContactNames = mutableListOf<String>()
    var mKeys : MutableList<String> = mutableListOf()


    var mContactPhoneAdapter = PhoneContactAdapter(mContactPhones)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        rbtEmail.isChecked = true

        mPresenter = ContactPresenter(mView = this, prefs = PreferencesUtil(this))
        mPresenter.getContactInfo()



        setUpViews()
    }

    private fun setUpViews() {

        mContactNames.add("Selecione o destinatário")


        radioGroupContactMean.onCheckedChange({ radioGroup, i ->

            val selectedIndex = radioGroup?.indexOfChild(radioGroup?.find(i))

            if (selectedIndex == 0) {
                emailContact.visibility = VISIBLE
                phoneContact.visibility = GONE
                buttonSend.text = getString(R.string.send)
            } else {
                emailContact.visibility = GONE
                phoneContact.visibility = VISIBLE
                buttonSend.text = getString(R.string.call)
            }
        })

        val slide = Slide()
        slide.duration = 1000
        window.enterTransition = slide
    }

    override fun retrievedContactInfo(contactsInfo: LinkedHashMap<String, ContactInfo>) {

        mContactInfo = contactsInfo

        mContactNames.addAll(contactsInfo.values.toMutableList().filter { it.type == ContactType.email.name }.listWithNames())
        mContactPhones.addAll(contactsInfo.values.filter { it.type == ContactType.phone.name })
        mKeys.addAll(contactsInfo.keys)

        // val adapter = ArrayAdapter<String>(this, R.layout.item_spinner, mContactNames)
        val adapter = SpinnerCustomAdapter(this, mContactNames, R.layout.item_spinner, R.layout.spinner_dropdown_item)

        spinner.adapter = adapter

        phoneList.adapter = mContactPhoneAdapter
        val mLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        phoneList.layoutManager = mLayoutManager

    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressWheel.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressWheel.visibility = GONE
    }


}
