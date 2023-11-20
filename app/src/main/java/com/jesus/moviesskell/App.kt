package com.jesus.moviesskell

import android.app.Application
import com.jesus.moviesskell.di.networkModule
import com.jesus.moviesskell.di.repositoryModule
import com.jesus.moviesskell.di.storageModule
import com.jesus.moviesskell.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                storageModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}