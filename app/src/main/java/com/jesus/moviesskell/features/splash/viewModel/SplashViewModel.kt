package com.jesus.moviesskell.features.splash.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jesus.moviesskell.storage.PreferencesManager

class SplashViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    private val _isFirstLaunch = MutableLiveData<Boolean>()
    val isFirstLaunch: LiveData<Boolean> = _isFirstLaunch
    fun checkPreferences() {
        _isFirstLaunch.postValue(this.preferencesManager.isFirstLaunch())
    }
}