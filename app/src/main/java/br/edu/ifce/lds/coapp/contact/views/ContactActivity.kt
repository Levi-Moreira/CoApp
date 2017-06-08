package br.edu.ifce.lds.coapp.contact.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.view.View.*
import android.widget.LinearLayout
import android.widget.Toast
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.layout.activity_contact
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.adapters.PhoneContactAdapter
import br.edu.ifce.lds.coapp.contact.adapters.SpinnerCustomAdapter
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.entities.ContactType
import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import br.edu.ifce.lds.coapp.utils.listWithNames
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_contact.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.find
import org.jetbrains.anko.onCheckedChange
import org.jetbrains.anko.onClick


class ContactActivity : BaseActivity(), ContactView, PhoneContactAdapter.OnClickPhoneCallback {


    lateinit var mPresenter: ContactPresenter

    lateinit var mContactInfo: LinkedHashMap<String, ContactInfo>

    var mContactPhones = mutableListOf<ContactInfo>()
    var mContactNames = mutableListOf<String>()
    var mKeys: MutableList<String> = mutableListOf()


    lateinit var mContactPhoneAdapter: PhoneContactAdapter


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_contact)

        rbtEmail.isChecked = true

        mContactPhoneAdapter = PhoneContactAdapter(mContactPhones, this)
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
                phoneContact.visibility = INVISIBLE
                buttonSend.text = getString(br.edu.ifce.lds.coapp.R.string.send)
            } else {
                emailContact.visibility = INVISIBLE
                phoneContact.visibility = VISIBLE
                buttonSend.text = getString(br.edu.ifce.lds.coapp.R.string.call)
            }
        })

        buttonSend.onClick {
            if (rbtPhone.isChecked) {
                val number = mContactPhoneAdapter.getSelectedPhone()
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + number)

                Dexter.withActivity(this)
                        .withPermission(android.Manifest.permission.CALL_PHONE)
                        .withListener(object : PermissionListener {
                            @SuppressLint("MissingPermission")
                            override fun onPermissionGranted(response: PermissionGrantedResponse) {/* ... */
                                startActivity(callIntent)
                            }

                            override fun onPermissionDenied(response: PermissionDeniedResponse) {/* ... */
                            }

                            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {/* ... */
                            }
                        }).check()

            }
        }

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
        val mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        phoneList.layoutManager = mLayoutManager

    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Error", android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressWheel.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressWheel.visibility = GONE
    }

    override fun onClickPhone(pos: Int) {

        mContactPhoneAdapter.selectedPos = pos
        mContactPhoneAdapter.notifyDataSetChanged()
        buttonSend.enabled = true
    }


}
