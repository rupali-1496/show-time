package com.example.showtime.ui.movie;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitproj.Adapter;
import com.example.showtime.R;
import com.example.showtime.lttsPlayer.Player;
import com.example.showtime.retrofit.repository.MovieRepo;
import com.example.showtime.retrofit.model.ApiResponse;
import com.example.showtime.utils.AppConstants;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView= null;
    private Adapter moviesAdapter = null;
    public static ArrayList<ApiResponse> movieList = null;

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

        moviesAdapter = new Adapter();
        moviesAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                                                 @Override
                                                 public void onItemClick(int position, @NotNull View v) {
                                                     showMovieDetail(movieList.get(position));
                                                 }
                                             });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }

    private void showMovieDetail(ApiResponse movieList) {
        Dialog myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.fragment_detail);
        ImageView moviePoster = myDialog.findViewById(R.id.movie_poster);
        TextView ratings = myDialog.findViewById(R.id.ratings);
        TextView description = myDialog.findViewById(R.id.descrition);
        Button watchNow = myDialog.findViewById(R.id.watch_now);

        watchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Player.class);
                startActivity(intent);
            }
        });

        ratings.setText(movieList.getRating().toString());
        description.setText(movieList.getOverview());

        String imgPosterUrl = AppConstants.IMG_BASE_URL + movieList.getBackdropPath();
        Glide.with(moviePoster.getContext())
                .load(imgPosterUrl)
                .into(moviePoster);

        myDialog.show();
    }

    private void getMovies() {
        if (movieList == null) {
            MovieRepo mv = new MovieRepo();
            mv.getPopularMovies(18, 2014, AppConstants.API_KEY);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moviesAdapter.updateMovies(movieList);
                    recyclerView.setAdapter(moviesAdapter);
                }
            }, 2000);
        }else{
            moviesAdapter.updateMovies(movieList);
            recyclerView.setAdapter(moviesAdapter);
        }
    }

/*
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 200:
                        Log.d("MovieFragment","200rupali_movieList: "+MovieRepo.movies);
                        //List<ApiResponse> movieList = new ArrayList<>();
                        moviesAdapter.updateMovies(MovieRepo.movies);

                        recyclerView.setAdapter(moviesAdapter);
                        break;
                    case 401:

                        break;
                    default:

                }
            }
        };

*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.genre_fragment,container,false);
    }
}
