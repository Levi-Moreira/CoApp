package br.edu.ifce.lds.coapp.utils

import android.widget.EditText
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo

/**
 * Helper and extension functions for better development
 * Developed by: ellcash_levi
 */

val EditText.isValid: Boolean
    get() = !this.text.toString().isEmpty()

val EditText.isValidEmail: Boolean
    get() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

fun List<ContactInfo>.listWithNames(): MutableList<String> {

    var names = mutableListOf<String>()

    for (contact in this) {
        names.add(contact.name)
    }

    return names
}

