package br.edu.ifce.lds.coapp.contact.entities

import com.orm.SugarRecord

/**
 * Created by ellca on 06/06/2017.
 */
enum class ContactType {
    email, phone, none
}

const val ContactInfoFirebaseKey = "contact_info"

open class ContactInfo(var type: ContactType = ContactType.none, var name: String = "", var info: String = "") : SugarRecord<ContactInfo>() {

}
