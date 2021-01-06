package com.github.azdrachak.aafundamentals.network

import com.github.azdrachak.aafundamentals.API_KEY
import com.github.azdrachak.aafundamentals.LANG
import com.github.azdrachak.aafundamentals.data.tmdb.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("configuration?api_key=$API_KEY")
    suspend fun getConfiguration(): Configuration

    @GET("movie/now_playing?api_key=$API_KEY&language=$LANG&page=1")
    suspend fun getMovies(): Movies

    @GET("genre/movie/list?api_key=$API_KEY&language=$LANG")
    suspend fun getGenres(): Genres

    @GET("movie/{id}/credits?api_key=$API_KEY&language=$LANG")
    suspend fun getCredits(@Path("id") movieId: Int): Credits

    @GET("movie/{id}?api_key=$API_KEY&language=$LANG")
    suspend fun getDetails(@Path("id") movieId: Int): Details
}
