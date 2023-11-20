package com.jesus.moviesskell.di

import com.jesus.moviesskell.features.movieDetail.viewModel.MovieDetailViewModel
import com.jesus.moviesskell.features.movies.viewModel.MoviesViewModel
import com.jesus.moviesskell.features.onboarding.viewModel.OnboardingViewModel
import com.jesus.moviesskell.features.splash.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}