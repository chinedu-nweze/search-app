package com.search.app.module

import com.search.app.repository.ISearchRickAndMortyRepository
import com.search.app.repository.SearchRickAndMortyRepository
import com.search.app.service.ApiService
import com.search.app.ui.view.CharacterDetailsScreenViewModel
import com.search.app.ui.view.MainScreenViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

val appModules = module {

    factoryOf(::SearchRickAndMortyRepository) bind ISearchRickAndMortyRepository::class

    single { Json { ignoreUnknownKeys = true } }
    singleOf(::provideRetrofit)
    singleOf(::provideOkhttpClient)
    singleOf(::provideOkhttpLoggingInterceptor)
    single { get<Retrofit>().create<ApiService>() }

    viewModelOf(::MainScreenViewModel)
    viewModelOf(::CharacterDetailsScreenViewModel)
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    json: Json
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

private fun provideOkhttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    val client = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }
    return client.build()
}

private fun provideOkhttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return logging
}
