package com.miu.edu.sports.news.information.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {

    var firstName: String?
    var lastName: String?
    var userEmail: String?
    var userPassword: String?

    fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)
    fun unRegisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)
    fun logOutUser()
}

class SharedPreferenceStorage @Inject constructor(@ApplicationContext context: Context) : PreferenceStorage {

   private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override var firstName
            by StringPreference(prefs, PREFS_FIRST_NAME, "")

    override var lastName
            by StringPreference(prefs, PREF_LAST_NAME, "")

    override var userEmail
            by StringPreference(prefs, PREF_USER_EMAIL, "")

    override var userPassword
            by StringPreference(prefs, PREF_PASSWORD, "")

    override fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unRegisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
    }


    override fun logOutUser() {
        firstName = ""
        lastName = ""
        userEmail = ""
        userPassword = ""
    }
    companion object {
        const val PREFS_NAME = "sports-news"
        const val PREFS_FIRST_NAME = "pref_first_name"
        const val PREF_LAST_NAME = "pref_last_name"
        const val PREF_USER_EMAIL = "pref_user_email"
        const val PREF_PASSWORD = "pref_password"
    }

}

class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit {
            putString(name, value)
        }
    }
}

