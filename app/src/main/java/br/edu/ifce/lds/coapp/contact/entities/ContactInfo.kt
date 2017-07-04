package br.edu.ifce.lds.coapp.contact.entities

import com.orm.SugarRecord

/**
 * Created by ellca on 06/06/2017.
 */
enum class ContactType {
    email, phone
}

const val ContactInfoFirebaseKey = "contact_info"

open class ContactInfo(var type: String = "", var name: String = "", var info: String = "") : SugarRecord<ContactInfo>() {

}
