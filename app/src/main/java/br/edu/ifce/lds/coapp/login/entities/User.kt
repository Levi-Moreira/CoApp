package br.edu.ifce.lds.coapp.login.entities

/**
 * Models a user
 */
data class User(val username: String,
                val password: String,
                val first_name: String = "",
                val last_name: String = "",
                val is_staff: Boolean = false,
                val is_superuser: Boolean = false
)