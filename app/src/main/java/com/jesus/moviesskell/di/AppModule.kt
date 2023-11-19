package com.jesus.moviesskell.di

import MoviesPagerSource
import com.jesus.moviesskell.network.Api
import com.jesus.moviesskell.network.services.MoviesApiService
import com.jesus.moviesskell.data.repository.MoviesRepository
import com.jesus.moviesskell.data.repository.MoviesRepositoryImpl
import com.jesus.moviesskell.features.movieDetail.viewModel.MovieDetailViewModel
import com.jesus.moviesskell.features.movies.viewModel.MoviesViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { interceptor() }
    single { okhttpClient(get()) }
    single { retrofit(get()) }
    single { moviesService(get()) }
    factory { MoviesPagerSource(get()) }
    single<MoviesRepository> { return@single MoviesRepositoryImpl(get(), get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}

//TODO: Isolate network layer in data section

private fun interceptor(): Interceptor =
    Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", Api.API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }


private fun okhttpClient(requestInterceptor: Interceptor): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(requestInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

private fun retrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun moviesService(retrofit: Retrofit): MoviesApiService =
    retrofit.create(MoviesApiService::class.java)