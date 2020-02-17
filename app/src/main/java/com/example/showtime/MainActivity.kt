package com.example.showtime


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.example.showtime.retrofit.APIInterface
import com.example.showtime.retrofit.ApiClient
import com.example.showtime.retrofit.ContentNodes
import javax.security.auth.callback.Callback
import android.widget.TextView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    val apiInterface: APIInterface? = ApiClient.getClient().create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val responseText: TextView

        responseText = findViewById(R.id.txt1);

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl

            Toast.makeText(applicationContext, "Welcome " + personName, Toast.LENGTH_SHORT).show()
        }

    }
    override fun onBackPressed() {
        Toast.makeText(applicationContext,"MainActivity", Toast.LENGTH_SHORT).show()
    }
}
