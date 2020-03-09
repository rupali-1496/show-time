package com.example.showtime.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitproj.Api;

import com.example.retrofitproj.MoviesAdapter;

import com.example.showtime.retrofit.Movie;
import com.example.showtime.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepo {

    public static ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Movie> list ;
    private static Response<Movie> response1 = null;

    private MoviesAdapter ma = new MoviesAdapter();

    private Retrofit retrofit = null;

    private Api api = null;

    public MovieRepo(){
        if(retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(AppConstants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        api = retrofit.create(Api.class);
    }

    public void getPopularMovies(int withGenres,int primary_release_year,String apiKey ) {
        Log.d("Harsh", "hii");

        api.getMovies(withGenres, primary_release_year, apiKey)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        response1 = response;
                        movies.addAll(response.body().getResults());
                        Log.d("list:", " " + movies.toString());
                        mutableLiveData.postValue(movies);
                        Log.d("list:123", "re " + movies.get(0).getPosterPath());
                        ma.updateMovies(movies);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }

                });
        if (list == null || list.size() == 0) {
            Log.d("harsh: ", "list94");

        }
    }
    public void getSearchMovies(String key,String query) {
        Log.d("Harsh", "hii");

        api.searchMovie(key,query)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        response1 = response;
                        movies.addAll(response.body().getResults());
                        Log.d("list:", " " + movies.toString());
                        Log.d("list:123", "re " + movies.get(0).getPosterPath());
                        ma.updateMovies(movies);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                    }

                });
    }
}
