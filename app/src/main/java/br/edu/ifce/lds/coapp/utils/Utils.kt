package br.edu.ifce.lds.coapp.utils

import android.widget.EditText

/**
 * Created by ellca on 01/06/2017.
 */

val EditText.isValid: Boolean
    get() = !this.text.toString().isEmpty()

val EditText.isValidEmail: Boolean
    get() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

