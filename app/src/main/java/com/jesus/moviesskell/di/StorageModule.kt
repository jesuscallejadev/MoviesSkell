package com.jesus.moviesskell.di

import com.jesus.moviesskell.storage.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single { PreferencesManager(androidContext()) }
}