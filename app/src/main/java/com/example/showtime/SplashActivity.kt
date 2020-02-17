package com.example.showtime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.showtime.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val sharedPreferences: SharedPreferences = this.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE)
            val sdp = sharedPreferences.getBoolean("login",false)

           if(sdp) {
               startActivity(Intent(this, Main2Activity::class.java))
           }else{
               startActivity(Intent(this, LoginActivity::class.java))
           }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }


}
