package com.example.retrofitproj


import com.example.showtime.retrofit.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("with_genres") with_genres: Int?,
        @Query("primary_release_year") primary_release_year: Int?,
        @Query("api_key") apiKey: String?
    ): Call<Movie?>?

    @GET("3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?
    ): Call<Movie?>?

}