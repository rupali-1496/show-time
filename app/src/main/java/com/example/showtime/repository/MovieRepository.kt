package com.example.showtime.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitproj.Api
import com.example.retrofitproj.GetMoviesResponse

import com.example.retrofitproj.MoviesAdapter
import com.example.showtime.retrofit.Movie
import com.example.showtime.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private var api: Api ? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }


    fun getPopularMovies(withGenres:Int,primary_release_year : Int,apiKey : String): MutableLiveData<List<Movie>> {
        Log.d("Harsh","hii")
        var bodyList = ArrayList<Movie>()
        var body = MutableLiveData<List<Movie>>()
        var ma = MoviesAdapter()
        api?.getMovies(withGenres,primary_release_year,apiKey)!!
            .enqueue(object : Callback<Movie?> {
                override fun onResponse(
                    call: Call<Movie?>,
                    response: Response<Movie?>
                ) {
                     var body1 = response.body()?.results!!
                    Log.d("Harsh","hii2535: "+response.code()+" responsehh: "+body1)
                     ma.updateMovies(body1)
                    body.value = body1
                    body.postValue(body1)
                    //body= body1
                  //  body
                    Log.d("Harsh","hii25345: "+response.code()+" responsehh: "+body1)



                    /*    if (responseBody != null) {
                            Log.d("Harsh","hii3: "+responseBody.title)

                            body.value = responseBody

                            Log.d("Repository", "Movies: ${responseBody.title}")
                   */    }


                override fun onFailure(call: Call<Movie?>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                    //body.clear()
                }
            })

        return body
    }



}