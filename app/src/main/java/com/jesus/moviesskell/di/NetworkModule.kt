package com.jesus.moviesskell.di

import com.jesus.moviesskell.network.manager.NetworkManager
import org.koin.dsl.module

val networkModule = module {
    single { NetworkManager() }
    single { get<NetworkManager>().provideInterceptor() }
    single { get<NetworkManager>().provideOkHttpClient(get()) }
    single { get<NetworkManager>().provideRetrofit(get()) }
    single { get<NetworkManager>().provideMoviesService(get()) }
}