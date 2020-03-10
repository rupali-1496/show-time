package com.example.showtime.retrofit.repository;


import android.util.Log;

import com.example.retrofitproj.Api;
import com.example.retrofitproj.Adapter;
import com.example.showtime.retrofit.model.ApiResponse;
import com.example.showtime.retrofit.RetrofitObj;
import com.example.showtime.ui.search.SearchActivity;
import com.example.showtime.ui.movie.MovieFragment;
import com.example.showtime.ui.drama.DramaFragment;
import com.example.showtime.ui.kids.KidsMovieFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {

    private static final String TAG = "MovieRepo";

    public static ArrayList<ApiResponse> movies = null;
    private Adapter ma = new Adapter();

    private static Api api = null;

    public void getPopularMovies(int withGenres,int primary_release_year,String apiKey ) {
        api = RetrofitObj.getRetrofitInstance();
        api.getMovies(withGenres, primary_release_year, apiKey)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body() != null) {
                            movies = response.body().getResults();
                            MovieFragment.movieList = movies;
                            ma.updateMovies(movies);
                            //mutableLiveData.postValue(movies);
                        /*    Message msg = MovieFragment.mHandler.obtainMessage(response.code());
                              msg.sendToTarget();
                        */
                        }else{
                            Log.d(TAG,"RESPONSE : NULL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) { }

                });

    }
    public void getSearchMovies(String key,String query) {
        api = RetrofitObj.getRetrofitInstance();
        api.searchMovie(key,query)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.body()!=null) {
                            movies = response.body().getResults();
                            SearchActivity.movieList = movies;
                            ma.updateMovies(movies);
                        }else{
                            Log.d(TAG,"RESPONSE : NULL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                    }

                });
    }

    public void getKidsMovie(String certification_country,String certification_lte,String api_key) {
        api = RetrofitObj.getRetrofitInstance();
        api.getKidsMovie(certification_country,certification_lte,api_key)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body() != null) {
                            movies = response.body().getResults();
                            Log.d(TAG, "movies: " + movies);
                            KidsMovieFragment.movieList = movies;
                            ma.updateMovies(movies);
                        }else{
                        Log.d(TAG,"RESPONSE : NULL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });
    }

    public void getDramas(Integer with_genres,String sort_by,Integer vote_count,String api_key) {
        api = RetrofitObj.getRetrofitInstance();
        api.getDramas(with_genres,sort_by,vote_count,api_key)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.body()!=null) {
                            movies = response.body().getResults();
                            Log.d(TAG, "movies: " + movies);
                            DramaFragment.movieList = movies;
                            ma.updateMovies(movies);
                        }else{
                            Log.d(TAG,"RESPONSE : NULL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });
    }
}
