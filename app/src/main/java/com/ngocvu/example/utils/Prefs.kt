package com.ngocvu.example.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Prefs @Inject constructor(@ApplicationContext context: Context){
    companion object {
        private const val PREF_ACCESS_TOKEN = "pref_access_token"
        private const val PREF_REFRESH_TOKEN = "pref_refresh_token"
        private const val PREF_IS_LOGGED_IN = "pref_is_logged_in"

    }

    var prefs: SharedPreferences = context.getSharedPreferences(
        "com.example.app", Context.MODE_PRIVATE
    )

    var accessToken: String? = null
        get() {
            return prefs.getString(PREF_ACCESS_TOKEN, null)
        }
        set(value) {
            field = value
            prefs.edit().putString(PREF_ACCESS_TOKEN, value).apply()
        }

    var refreshToken: String? = null
        get() {
            return prefs.getString(PREF_REFRESH_TOKEN, null)
        }
        set(value) {
            field = value
            prefs.edit().putString(PREF_REFRESH_TOKEN, value).apply()
        }

    var isLogged: Boolean = false
        get() {
            return prefs.getBoolean(PREF_IS_LOGGED_IN, false)
        }
        set(value) {
            field = value
            prefs.edit().putBoolean(PREF_IS_LOGGED_IN, value).apply()
        }

}