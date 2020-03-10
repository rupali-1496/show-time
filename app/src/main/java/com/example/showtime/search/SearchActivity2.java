package com.example.showtime.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitproj.MoviesAdapter;
import com.example.showtime.R;
import com.example.showtime.repository.MovieRepo;
import com.example.showtime.retrofit.Movie;
import com.example.showtime.utils.AppConstants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity2 extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private RecyclerView recyclerView= null;
    private MoviesAdapter moviesAdapter = null;
    public static ArrayList<Movie> movieList = null;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        recyclerView = findViewById(R.id.recyclerview_search);
        editText = findViewById(R.id.editText_search);

        moviesAdapter = new MoviesAdapter();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event!=null && (event.getKeyCode()==KeyEvent.KEYCODE_ENTER))||(actionId == EditorInfo.IME_ACTION_DONE)){
                    getSearch(v.getText().toString());
                }
                return false;
            }

        });
    }

    private void getSearch(final String s) {
        MovieRepo movieRepo = new MovieRepo();
        movieRepo.getSearchMovies(AppConstants.API_KEY,s);
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("SearchActivity2","movieList: ");
                moviesAdapter.updateMovies(MovieRepo.movies);
                Log.d("SearchActivity2","movieList: "+MovieRepo.movies);
                recyclerView.setAdapter(moviesAdapter);
            }
        },1000);
    }




}
