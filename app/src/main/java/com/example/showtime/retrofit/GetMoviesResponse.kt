package com.example.retrofitproj

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logo_path: String,
    @SerializedName("name") val name: Int,
    @SerializedName("origin_country") val origin_country: String
)
