package com.ngocvu.example.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.google.gson.Gson
import com.ngocvu.example.data.res.AuthResData

class Prefs(context: Context) {


    companion object {
        private const val PREF_ACCESS_TOKEN = "pref_access_token"
        private const val PREF_REFRESH_TOKEN = "pref_refresh_token"
    }

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var accessToken: String? = null
        get() {
            return preferences.getString(PREF_ACCESS_TOKEN, null)
        }
        set(value) {
            field = value
            preferences.edit().putString(PREF_ACCESS_TOKEN, value).apply()
        }

    var refreshToken: String? = null
        get() {
            return preferences.getString(PREF_REFRESH_TOKEN, null)
        }
        set(value) {
            field = value
            preferences.edit().putString(PREF_REFRESH_TOKEN, value).apply()
        }
}