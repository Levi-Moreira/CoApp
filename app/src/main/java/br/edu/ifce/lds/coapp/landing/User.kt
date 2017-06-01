package br.edu.ifce.lds.coapp.landing

import java.util.prefs.Preferences

/**
 * Created by ellca on 01/06/2017.
 */
data class User(val name: String, val email: String, val cpf: String = "", val uid: String = "", val password: String = "") {

}