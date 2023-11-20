package com.jesus.moviesskell.domain.models.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jesus.moviesskell.R

enum class OnboardingScene(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val animation: Int
) {

    FIRST(R.string.onboarding_first_title, R.string.onboarding_first_description, R.raw.movies),
    SECOND(R.string.onboarding_second_title, R.string.onboarding_second_description,R.raw.scroll),
    THIRD(R.string.onboarding_third_title, R.string.onboarding_third_description, R.raw.camera)
}