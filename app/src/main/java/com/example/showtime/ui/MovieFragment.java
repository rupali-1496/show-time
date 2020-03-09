package com.example.showtime.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.retrofitproj.MoviesAdapter;
import com.example.showtime.R;
import com.example.showtime.repository.MovieRepo;
import com.example.showtime.repository.MovieRepository;
import com.example.showtime.retrofit.Movie;
import com.example.showtime.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MovieFragment extends Fragment {

    private static final String TAG = "MovieFragment";
    private RecyclerView recyclerView= null;
    private MoviesAdapter moviesAdapter = null;
    private List<Movie> movieList = new ArrayList<>();


    public static MovieFragment getInstance(){
        return new MovieFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getMovies();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        moviesAdapter = new MoviesAdapter();
        recyclerView.setHasFixedSize(true);
       // recyclerView.setAdapter(moviesAdapter);
        Log.d("MovieFragment","hii34");
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

    }

    private void getMovies() {
        //MovieRepository mv = new MovieRepository();
        MovieRepo mv = new MovieRepo();
        mv.getPopularMovies(18,2014,AppConstants.API_KEY);
     //   Log.d("MovieFragment","rupali_movieList: "+movieList);
      //  moviesAdapter.updateMovies((ArrayList<Movie>) movieList);

        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moviesAdapter.updateMovies(MovieRepo.movies);
                Log.d("MovieFragment","rupali_movieList: "+MovieRepo.movies);
                recyclerView.setAdapter(moviesAdapter);
            }
        },5000);
        }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie,container,false);
    }
}
