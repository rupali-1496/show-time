package com.example.showtime.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofitproj.Adapter;
import com.example.showtime.R;
import com.example.showtime.retrofit.repository.MovieRepo;
import com.example.showtime.retrofit.model.ApiResponse;
import com.example.showtime.utils.AppConstants;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView= null;
    private Adapter moviesAdapter = null;
    public static ArrayList<ApiResponse> movieList = null;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerview_search);
        editText = findViewById(R.id.editText_search);

        moviesAdapter = new Adapter();

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
                Log.d("SearchActivity","movieList: ");
                moviesAdapter.updateMovies(MovieRepo.movies);
                Log.d("SearchActivity","movieList: "+MovieRepo.movies);
                recyclerView.setAdapter(moviesAdapter);
            }
        },1000);
    }




}
