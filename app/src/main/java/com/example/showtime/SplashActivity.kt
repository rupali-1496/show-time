package com.example.showtime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.showtime.ui.login.LoginActivity
import com.example.showtime.utils.AppConstants


class SplashActivity : AppCompatActivity() {

    private val timeOut:Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val sharedPreferences: SharedPreferences = this.getSharedPreferences(AppConstants.SHARED_PREFERENCE,Context.MODE_PRIVATE)
            val sdp = sharedPreferences.getBoolean(AppConstants.SHARED_PREF_KEY,false)

           if(sdp) {
               startActivity(Intent(this, HomeActivity::class.java))
           }else{
               startActivity(Intent(this, LoginActivity::class.java))
           }

            // close this activity
            finish()
        }, timeOut)
    }


}
