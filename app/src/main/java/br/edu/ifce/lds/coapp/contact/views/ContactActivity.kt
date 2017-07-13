package br.edu.ifce.lds.coapp.contact.views

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.v4.content.FileProvider
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.view.View.*
import android.widget.LinearLayout
import android.widget.Toast
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.layout.activity_contact
import br.edu.ifce.lds.coapp.application.CoAppApplication
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.adapters.AttachmentFilesAdapter
import br.edu.ifce.lds.coapp.contact.adapters.PhoneContactAdapter
import br.edu.ifce.lds.coapp.contact.adapters.SpinnerCustomAdapter
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import br.edu.ifce.lds.coapp.contact.entities.ContactType
import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import br.edu.ifce.lds.coapp.utils.afterTextChanged
import br.edu.ifce.lds.coapp.utils.findByName
import br.edu.ifce.lds.coapp.utils.listWithNames
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import kotlinx.android.synthetic.main.activity_contact.*
import org.jetbrains.anko.*
import java.io.File
import javax.inject.Inject


class ContactActivity : BaseActivity(), ContactView, PhoneContactAdapter.OnClickPhoneCallback, AttachmentFilesAdapter.PickFileCallback {


    //the presenter for this class
    @Inject lateinit var mPresenter: ContactPresenter

    //the list of contact info brought from the backend
    lateinit var mContactInfo: LinkedHashMap<String, ContactInfo>

    //filtered lists of phones and emails
    var mContactPhones = mutableListOf<ContactInfo>()
    var mContactNames = mutableListOf<String>()


    var mKeys: MutableList<String> = mutableListOf()

    //the adapter for the phone list
    lateinit var mContactPhoneAdapter: PhoneContactAdapter

    //file list for attachments
    var mFilesList = mutableListOf<Uri>()


    val mFilesAdapter = AttachmentFilesAdapter(mFilesList, this)

    // @Inject lateinit var mPrefs: PreferencesUtil

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_contact)

//        (application as CoAppApplication).appComponent.inject(this)
//
        DaggerContactViewComponent
                .builder()
                .contactViewModule(ContactViewModule(this))
                .build()
                .inject(this)
        //check the email option at first
        rbtEmail.isChecked = true

        //start up the adapter
        mContactPhoneAdapter = PhoneContactAdapter(mContactPhones, this)

        //start up the presenter
        //mPresenter = ContactPresenter(mView = this, prefs = mPrefs)

        //retrieve list of contacts from the backend
        mPresenter.getContactInfo()

        setUpViews()
    }

    /**
     * Set up clicks and listeners for some views
     */
    private fun setUpViews() {


        mContactNames.add("Selecione o destinatário")

        mFilesList.add(Uri.parse("android.resource://br.edu.ifce.lds.coapp/" + R.drawable.ic_add_file))

        filesList.adapter = mFilesAdapter
        filesList.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        //when changed the radio button selected, change the options shown
        radioGroupContactMean.onCheckedChange({ radioGroup, i ->

            val selectedIndex = radioGroup?.indexOfChild(radioGroup?.find(i))

            //if selected email
            if (selectedIndex == 0) {
                emailContact.visibility = VISIBLE
                phoneContact.visibility = INVISIBLE
                buttonSend.text = getString(R.string.send)
                message.text.clear()
                editTextSubject.text.clear()
                spinner.setSelection(0)
                val addButton = mFilesList.removeAt(mFilesList.size - 1)
                mFilesList.clear()
                mFilesList.add(addButton)
                mFilesAdapter.notifyDataSetChanged()
            } else {
                //if selected phone
                emailContact.visibility = INVISIBLE
                phoneContact.visibility = VISIBLE
                buttonSend.text = getString(R.string.call)
                mContactPhoneAdapter.selectedPos = -1
                mContactPhoneAdapter.notifyDataSetChanged()
            }

            checkButtonForSelection(selectedIndex)
        })

        //when clicks the "Enviar" or "Ligar" button
        buttonSend.onClick {

            //check if it is phone
            if (rbtPhone.isChecked) {
                val number = mContactPhoneAdapter.getSelectedPhone()
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + number)

                //before sending calling intent, check for permissions
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

            } else {
                //if not, it must be email

                val selectedName = mContactNames[spinner.selectedItemPosition]
                val contactInfo = mContactInfo.findByName(selectedName)

                if (contactInfo != null) {
                    val intent = Intent(Intent.ACTION_SEND_MULTIPLE)

                    intent.type = "text/html"
                    // intent.data = Uri.parse("mailto:") // only email apps should handle this

                    val addresses: Array<String> = arrayOf(contactInfo.info)
                    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "[Contato] CoAPP : " + editTextSubject.text.toString())
                    intent.putExtra(Intent.EXTRA_TEXT, message.text.toString())

                    val files: ArrayList<Uri> = arrayListOf()

                    for (file in mFilesList) {
                        if (mFilesList.indexOf(file) != mFilesList.size - 1)
                            files.add(FileProvider.getUriForFile(this, "br.edu.ifce.lds.coapp.droidninja.filepicker.provider", File(file.path)))
                    }
                    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files)

                    try {
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, "Você não tem nenhum aplicativo para envio de emails.", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }


        message.afterTextChanged {
            buttonSend.enabled = false

            if (!it.isEmpty() && spinner.selectedItemPosition != 0 && !editTextSubject.text.toString().isEmpty()) {
                buttonSend.enabled = true
            }
        }


        editTextSubject.afterTextChanged {
            buttonSend.enabled = false

            if (!it.isEmpty() && spinner.selectedItemPosition != 0 && !message.text.toString().isEmpty()) {
                buttonSend.enabled = true
            }
        }

        spinner.onItemSelectedListener {
            buttonSend.enabled = false

            if (!message.text.toString().isEmpty() && spinner.selectedItemPosition != 0 && !editTextSubject.text.toString().isEmpty()) {
                buttonSend.enabled = true
            }
        }
        setUpAnimations()
    }

    private fun checkButtonForSelection(selectedIndex: Int?) {
        if (selectedIndex == 0) {
            buttonSend.enabled = !message.text.toString().isEmpty() && spinner.selectedItemPosition != 0 && !editTextSubject.text.toString().isEmpty()
        } else {
            buttonSend.enabled = mContactPhoneAdapter.hasSelectedPos
        }

    }


    override fun retrievedContactInfo(contactsInfo: LinkedHashMap<String, ContactInfo>) {

        //save retreived information
        mContactInfo = contactsInfo

        //separate information
        mContactNames.addAll(contactsInfo.values.toMutableList().filter { it.type == ContactType.email }.listWithNames())
        mContactPhones.addAll(contactsInfo.values.filter { it.type == ContactType.phone })
        mKeys.addAll(contactsInfo.keys)

        //add emails to spinner
        val adapter = SpinnerCustomAdapter(this, mContactNames, R.layout.item_spinner, R.layout.spinner_dropdown_item)
        spinner.adapter = adapter

        //add phones to list
        phoneList.adapter = mContactPhoneAdapter
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        phoneList.layoutManager = mLayoutManager

    }

    /**
     * If an error occurs during loading
     */
    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    /**
     * Show loading progress wheel
     */
    override fun showLoading() {
        progressWheel.visibility = VISIBLE
    }

    /**
     * Hide loading progress wheel
     */
    override fun hideLoading() {
        progressWheel.visibility = GONE
    }

    /**
     * When user clicks on one of the phone numbers
     */
    override fun onClickPhone(pos: Int) {

        mContactPhoneAdapter.selectedPos = pos
        mContactPhoneAdapter.notifyDataSetChanged()
        buttonSend.enabled = true
    }


    /**
     * Animations for enter and exit activity
     */
    private fun setUpAnimations() {
        val slide = Slide()
        slide.duration = 1000
        window.enterTransition = slide
    }


    /**
     * Respond to clicking in the file list
     */
    override fun onClickFile(pos: Int) {
        if (pos == mFilesList.size - 1) {
            //before sending calling intent, check for permissions
            Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(object : PermissionListener {
                        @SuppressLint("MissingPermission")
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {/* ... */
                            pickFiles()
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {/* ... */
                        }

                        override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {/* ... */
                        }
                    }).check()
        } else {
            //
        }

    }

    /**
     * Calls picker activity to get files
     */
    private fun pickFiles() {
        FilePickerBuilder.getInstance().setMaxCount(5)
                .addFileSupport("Imagens", arrayOf(".png", ".jpg", ".JPEG", ".jpeg"))
                .setActivityTheme(R.style.AppTheme)
                .pickFile(this)
    }


    /**
     * Treat the receiving of documents from the picking activity
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            FilePickerConst.REQUEST_CODE_PHOTO -> {

            }
            FilePickerConst.REQUEST_CODE_DOC -> {
                var docPaths = data?.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS)
                if (docPaths != null)
                    addToView(docPaths)
            }

        }

    }

    /**
     * Push docs picked to the view
     */
    private fun addToView(docPaths: ArrayList<String>) {
        val first = mFilesList.removeAt(mFilesList.size - 1)
        //mFilesList.clear()

        for (path in docPaths) {
            mFilesList.add(Uri.parse(path))
            mFilesAdapter.notifyItemInserted(mFilesList.size - 1)
        }
        mFilesList.add(first)
    }


}
