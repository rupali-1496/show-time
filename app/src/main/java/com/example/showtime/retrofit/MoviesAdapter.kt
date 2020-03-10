package com.example.retrofitproj

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showtime.OnItemClickListener
import com.example.showtime.R
import com.example.showtime.retrofit.Movie
import com.example.showtime.utils.AppConstants





class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    private val movies: ArrayList<Movie> = ArrayList<Movie>()

    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("onCreateViewHolder", "imgPosterUrl: ")
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setOnItemClickListener(listener:OnItemClickListener ) {
        Log.d("Harsh1315","list")
        this.clickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View)
    }

    fun getItem(position: Int): Movie {
        return movies!!.get(position)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //val mv = getItem(position)
        val ctx = holder.moviePoster.context
        var imgUrl: String
        if (movies.size == 0) {
            imgUrl = AppConstants.IMG_BASE_URL + "/fa4PxEPRKWRyjzYje1jM4m30qzd.jpg"
        } else {
            imgUrl = AppConstants.IMG_BASE_URL + movies.get(position).posterPath
        }

        Glide.with(ctx).load(imgUrl)
            .placeholder(R.drawable.ic_search_black_24dp)
            .error(R.drawable.common_google_signin_btn_icon_dark_focused)
            .into(holder.moviePoster)
    }

    fun updateMovies(@NonNull movies: java.util.ArrayList<Movie>) {

        this.movies.clear()

        Log.d("Harsh96: ", "momvieshii: " + movies.toString())

        this.movies.addAll(movies)
        notifyDataSetChanged()
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var moviePoster: ImageView

        init {
            moviePoster = itemView.findViewById(R.id.item_movie_poster)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            clickListener?.onItemClick(adapterPosition,v)
        }
    }
}


/*
        init {
            moviePoster = itemView.findViewById(R.id.item_movie_poster)

            //click listener for Movie item
            itemView.setOnClickListener {
                val position = this.adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(getItem(position))
                }
            }
        }

 */


