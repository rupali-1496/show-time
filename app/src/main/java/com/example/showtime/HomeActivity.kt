package com.example.showtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.showtime.search.SearchActivity2
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    private var tabs: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs = findViewById(R.id.genres)
        viewPager = findViewById(R.id.view)

        tabs!!.addTab(tabs!!.newTab().setText("Movie"))
        tabs!!.addTab(tabs!!.newTab().setText("Tv Shows"))
        tabs!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = CustomPagerAdapter(this, supportFragmentManager)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        Log.d("HomeActivity","response: ")

      //  var d = MovieRepository()
     //   d.getPopularMovies(18,2014,AppConstants.API_KEY)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.search -> {
                    val intent = Intent(this, SearchActivity2::class.java)
                    startActivity(intent)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

    }
}
