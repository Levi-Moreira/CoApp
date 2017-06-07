package br.edu.ifce.lds.coapp.contact

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import br.edu.ifce.lds.coapp.utils.listWithNames
import kotlinx.android.synthetic.main.activity_contact.*
import android.widget.*
import br.edu.ifce.lds.coapp.contact.entities.ContactType
import org.jetbrains.anko.textColor


class ContactActivity : BaseActivity(), ContactView {

    lateinit var mPresenter: ContactPresenter

    lateinit var mContactInfo: List<ContactInfo>
    var contactNames = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        rbtEmail.isChecked = true

        mPresenter = ContactPresenter(mView = this, prefs = PreferencesUtil(this))
        mPresenter.getContactInfo()

        setUpViews()
    }

    private fun setUpViews() {

        contactNames.add("Selecione o destinatário")


        val slide = Slide()
        slide.duration = 1000
        window.enterTransition = slide
    }

    override fun retrievedContactInfo(contactsInfo: List<ContactInfo>) {

        mContactInfo = contactsInfo

        contactNames.addAll(contactsInfo.filter { it.type == ContactType.email.name }.listWithNames())

        // val adapter = ArrayAdapter<String>(this, R.layout.item_spinner, contactNames)
        val adapter = SpinnerCustomAdapter(this, contactNames, R.layout.item_spinner, R.layout.spinner_dropdown_item)
        //adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinner.adapter = adapter

        Toast.makeText(this, "Brought stuff", Toast.LENGTH_SHORT).show()

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
