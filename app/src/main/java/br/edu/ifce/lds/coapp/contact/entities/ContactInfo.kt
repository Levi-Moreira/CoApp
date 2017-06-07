package br.edu.ifce.lds.coapp.contact.entities

/**
 * Created by ellca on 06/06/2017.
 */
enum class ContactType {
    EMAIL, PHONE
}

data class ContactInfo(var type: ContactType, var name: String, var info: String)
