package com.jesus.moviesskell.features.onboarding.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jesus.moviesskell.storage.PreferencesManager

private const val TAG = "OnboardingViewModel"
class OnboardingViewModel(private val preferencesManager: PreferencesManager): ViewModel() {

    private val _onboardingFinished = MutableLiveData<Boolean>()
    val onboardingFinished: LiveData<Boolean> = _onboardingFinished
    fun disableOnboarding() {
        this.preferencesManager.setFirstLaunch(false)
        Log.i(TAG, "Onboarding Preference disabled")
        _onboardingFinished.postValue(true)
    }
}
