package com.github.azdrachak.aafundamentals.network

import com.github.azdrachak.aafundamentals.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@ExperimentalSerializationApi
object Retrofit {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val instance: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val service: TheMovieDbApi = instance.create(TheMovieDbApi::class.java)
}
