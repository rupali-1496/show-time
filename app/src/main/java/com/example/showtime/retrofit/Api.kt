package com.example.retrofitproj

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "aca35d034c9330fea578760de19df054",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String = "aca35d034c9330fea578760de19df054",
        @Query("query") query : String = "Ant Man",
        @Query("page") page : Int
    ): Call<GetMoviesResponse>

    object MoviesRepository {

        private val api: Api

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            api = retrofit.create(Api::class.java)
        }

        fun getPopularMovies(page: Int = 1,
                             onSuccess: (movies: List<Movie>) -> Unit,
                             onError: () -> Unit
        ) {
            api.getPopularMovies(page = page)
                .enqueue(object : Callback<GetMoviesResponse> {
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                                Log.d("Repository", "Movies: ${responseBody.movies}")
                            } else {
                                onError.invoke()
                                Log.d("Repository", "Failed to get response")
                            }
                        }else{
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }


}