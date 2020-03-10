package com.example.showtime

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.showtime.ui.movie.MovieFragment
import com.example.showtime.ui.drama.DramaFragment
import com.example.showtime.ui.kids.KidsMovieFragment
import com.example.showtime.utils.AppConstants.*

class ViewPagerAdapter(val ctx : Context, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {


        when(position){
            0 -> {
                return MovieFragment.getInstance()
            }
            1->{
                return DramaFragment.getInstance()
            }
            2->{
                return KidsMovieFragment.getInstance()
            }
        }
        return MovieFragment.getInstance()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabLayouts[position] as CharSequence?
    }

}