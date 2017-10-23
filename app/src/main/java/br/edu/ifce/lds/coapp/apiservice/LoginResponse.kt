package br.edu.ifce.lds.coapp.apiservice

import br.edu.ifce.lds.coapp.contact.entities.Coworking
import br.edu.ifce.lds.coapp.login.entities.User

/**
 * Created by lds on 10/23/17.
 */
class LoginResponse() {
    var user: User? = null
    var private_token: String = ""
    var coworking: Coworking? = null
}