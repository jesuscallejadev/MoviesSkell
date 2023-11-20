package com.jesus.moviesskell.di

import MoviesPagerSource
import com.jesus.moviesskell.network.Api
import com.jesus.moviesskell.network.services.MoviesApiService
import com.jesus.moviesskell.data.repository.MoviesRepository
import com.jesus.moviesskell.data.repository.MoviesRepositoryImpl
import com.jesus.moviesskell.features.movieDetail.viewModel.MovieDetailViewModel
import com.jesus.moviesskell.features.movies.viewModel.MoviesViewModel
import com.jesus.moviesskell.features.onboarding.viewModel.OnboardingViewModel
import com.jesus.moviesskell.features.splash.viewModel.SplashViewModel
import com.jesus.moviesskell.network.manager.NetworkManager
import com.jesus.moviesskell.storage.PreferencesManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { PreferencesManager(androidContext()) }
    single { NetworkManager() }
    single { get<NetworkManager>().provideInterceptor() }
    single { get<NetworkManager>().provideOkHttpClient(get()) }
    single { get<NetworkManager>().provideRetrofit(get()) }
    single { get<NetworkManager>().provideMoviesService(get()) }
    factory { MoviesPagerSource(get()) }
    single<MoviesRepository> { return@single MoviesRepositoryImpl(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}