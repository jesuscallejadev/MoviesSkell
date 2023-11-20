package com.jesus.moviesskell.storage

import android.content.Context
import android.content.SharedPreferences
import com.jesus.moviesskell.Constants

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(Constants.Preferences.APP_PREFS, Context.MODE_PRIVATE)
    private val prefsEditor: SharedPreferences.Editor = prefs.edit()

    fun isFirstLaunch(): Boolean = prefs.getBoolean(Constants.Preferences.IS_FIRST_LAUNCH, true)

    fun setFirstLaunch(isFirstLaunch: Boolean) {
        prefsEditor.putBoolean(Constants.Preferences.IS_FIRST_LAUNCH, isFirstLaunch)
        prefsEditor.commit()
    }
}