package br.edu.ifce.lds.coapp.firebase

import br.edu.ifce.lds.coapp.contact.entities.ContactInfo

/**
 * Created by ellca on 06/06/2017.
 */


data class GetContactsInfoResponse(val contacts_info: List<ContactInfo>)