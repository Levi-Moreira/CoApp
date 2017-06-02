package br.edu.ifce.lds.coapp.utils

import android.widget.EditText

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

