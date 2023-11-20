package com.jesus.moviesskell.features.onboarding.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jesus.moviesskell.storage.PreferencesManager

class OnboardingViewModel(private val preferencesManager: PreferencesManager): ViewModel() {

    private val _onboardingFinished = MutableLiveData<Boolean>()
    val onboardingFinished: LiveData<Boolean> = _onboardingFinished
    fun disableOnboarding() {
        this.preferencesManager.setFirstLaunch(false)
        _onboardingFinished.postValue(true)
    }
}
