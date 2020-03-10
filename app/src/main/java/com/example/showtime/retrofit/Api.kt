package com.example.retrofitproj


import com.example.showtime.retrofit.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("with_genres") with_genres: Int?,
        @Query("primary_release_year") primary_release_year: Int?,
        @Query("api_key") apiKey: String?
    ): Call<ApiResponse?>?

    @GET("3/discover/movie")
    fun getKidsMovie(
        @Query("certification_country") certification_country: String?,
        @Query("certification.lte") certification_lte: String?,
        @Query("api_key") apiKey: String?
    ): Call<ApiResponse?>?

    @GET("3/discover/movie")
    fun getDramas(
        @Query("with_genres") with_genres: Int?,
        @Query("sort_by") sort_by: String?,
        @Query("vote_count.gte") vote_count_gte :Int?,
        @Query("api_key") apiKey: String?
    ): Call<ApiResponse?>?

    @GET("3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?
    ): Call<ApiResponse?>?

}