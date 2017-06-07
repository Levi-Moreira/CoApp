package br.edu.ifce.lds.coapp.application


import br.edu.ifce.lds.coapp.landing.entities.User
import br.edu.ifce.lds.coapp.utils.PreferencesUtil


/**
 * This class saves up or clears out the user info from shared preferences
 */

class UserSession(private val mPrefs: PreferencesUtil) {

    /**
     * Save all user info to shared preferences

     * @param user The logged user
     */
    fun signin(user: User) {
        mPrefs.setString(PreferencesUtil.UID, user.uid)
        mPrefs.setString(PreferencesUtil.LOGGED, "true")
        mPrefs.setString(PreferencesUtil.USERNAME, user.name)
        mPrefs.setString(PreferencesUtil.EMAIL, user.email)
        mPrefs.setString(PreferencesUtil.PASSWORD, user.password)


    }

    /**
     * Clears all data from the shared preferences
     */
    fun logout() {
        mPrefs.clearData()

    }

    fun setToken(token: String) {
        mPrefs.setString(PreferencesUtil.TOKEN, token)
    }
}