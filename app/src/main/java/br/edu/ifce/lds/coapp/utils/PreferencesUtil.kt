package br.edu.ifce.lds.coapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * Holds all user  important data in shared preferences
 */

class PreferencesUtil
/**
 * Class constructor, take a context to build

 * @param context
 */
(context: Context) {


    private val prefs: SharedPreferences


    init {
        prefs = context.getSharedPreferences(PFX, MODE_PRIVATE)
    }

    val isLogged: Boolean
        get() {
            val res = prefs.getString(LOGGED, "false")

            return "true" == res
        }

    fun clearData() {
        prefs.edit().clear().commit()
    }

    /*
    * Some helpers getters and setters
    * */


    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).commit()
    }


    companion object {

        private val PFX = "Acts"
        val LOGGED = PFX + ".logged"
        val UID = PFX + ".uid"
        val USERNAME = PFX + ".username"
        val PASSWORD = PFX + ".password"
        val EMAIL = PFX + ".email"

    }

}
