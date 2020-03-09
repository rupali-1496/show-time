package com.example.retrofitproj

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TableLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.showtime.OnItemClickListener
import com.example.showtime.R
import com.example.showtime.retrofit.Movie
import com.example.showtime.utils.AppConstants

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    private val movies: ArrayList<Movie> = ArrayList<Movie>()
    private val listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("onCreateViewHolder","imgPosterUrl: ")
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie {
        return movies!!.get(position)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //val mv = getItem(position)
        val ctx = holder.moviePoster.context
        var imgUrl :String
        if(movies.size==0){
            imgUrl = AppConstants.IMG_BASE_URL+"/fa4PxEPRKWRyjzYje1jM4m30qzd.jpg"
        }else{
            imgUrl = AppConstants.IMG_BASE_URL+ movies.get(position).posterPath
        }

        Log.d("imgPosterUrl","imgPosterUrl: " + imgUrl)

        Glide.with(ctx).load(imgUrl)
            .placeholder(R.drawable.ic_search_black_24dp)
                .error(R.drawable.common_google_signin_btn_icon_dark_focused)
                .into(holder.moviePoster)


      //  holder.bind(movies!!.get(position))
    }

    fun updateMovies(movies: java.util.ArrayList<Movie>) {

     //   this.movies.clear()

        Log.d("MovieAdapter: ", "momvieshii: " + movies.toString())

        this.movies.addAll(movies)
        notifyDataSetChanged()
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moviePoster: ImageView

        init {
            moviePoster = itemView.findViewById(R.id.item_movie_poster)

            //click listener for Movie item
            itemView.setOnClickListener {
                val position = this.adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position))
                }
            }
        }
    }
}

/*
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val poster: ImageView

        init {
            poster = itemView.findViewById(R.id.item_movie_poster)

            itemView.setOnClickListener{
                val position = this@MovieViewHolder.adapterPosition
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(getItem(position))
            }

        }
    }
*/



/*
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(AppConstants.IMG_BASE_URL+movie.backdropPath)
                .transform(CenterCrop())
                .into(poster)
        }
    }
*/

