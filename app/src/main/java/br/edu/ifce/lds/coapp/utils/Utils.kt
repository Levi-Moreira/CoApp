package br.edu.ifce.lds.coapp.utils

import android.text.Editable
import android.text.TextWatcher
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

/**
 * Either returns a contact containing the given name or null
 */
fun LinkedHashMap<String, ContactInfo>.findByName(name: String): ContactInfo?{
    for ((key, contact) in this) {
        if(contact.name == name){
            return contact
        }
    }

    return null
}

/**
 * Lambda for edit text textChangeListener
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

