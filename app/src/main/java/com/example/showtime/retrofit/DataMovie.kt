package com.example.retrofitproj

import com.google.gson.annotations.SerializedName

data class DataMovie(

    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("results") val results: ArrayList<DataMovie>,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String



/*
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<Movie>,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("title") val title: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String
*/
)