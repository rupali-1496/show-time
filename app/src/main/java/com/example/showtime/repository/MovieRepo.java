package com.example.showtime.repository;

import android.os.Message;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitproj.Api;

import com.example.retrofitproj.MoviesAdapter;

import com.example.showtime.retrofit.Movie;
import com.example.showtime.retrofit.RetrofitObj;
import com.example.showtime.search.SearchActivity2;
import com.example.showtime.ui.MovieFragment;
import com.example.showtime.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepo {

    public static ArrayList<Movie> movies = null;
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Movie> list ;
    private static Response<Movie> response1 = null;

    private MoviesAdapter ma = new MoviesAdapter();

    private static Api api = null;

    public void getPopularMovies(int withGenres,int primary_release_year,String apiKey ) {
        Log.d("Harsh", "hii");
        api = RetrofitObj.getRetrofitInstance();
        api.getMovies(withGenres, primary_release_year, apiKey)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        movies = response.body().getResults();
                        MovieFragment.movieList = movies;
                        ma.updateMovies(movies);
                        //mutableLiveData.postValue(movies);
                        /*    Message msg = MovieFragment.mHandler.obtainMessage(response.code());
                              msg.sendToTarget();
                        */
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) { }

                });

    }
    public void getSearchMovies(String key,String query) {
        Log.d("Harsh", "hii");
        api = RetrofitObj.getRetrofitInstance();
        api.searchMovie(key,query)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {

                        movies = response.body().getResults();
                        SearchActivity2.movieList = movies;
                        ma.updateMovies(movies);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.d("list:", "onFailure ");
                    }

                });
    }
}
