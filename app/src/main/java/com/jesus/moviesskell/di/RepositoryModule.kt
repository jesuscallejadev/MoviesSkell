package com.jesus.moviesskell.di

import MoviesPagerSource
import com.jesus.moviesskell.data.repository.MoviesRepository
import com.jesus.moviesskell.data.repository.MoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { MoviesPagerSource(get()) }
    single<MoviesRepository> { return@single MoviesRepositoryImpl(get(), get()) }
}