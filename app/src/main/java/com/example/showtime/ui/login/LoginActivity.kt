package com.example.showtime.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.showtime.HomeActivity
import com.example.showtime.R
import com.example.showtime.utils.AppConstants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    val RC_SIGN_IN =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //BINDING UI elements with kotlin
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        var googleLogin = findViewById<SignInButton>(R.id.sign_in_button)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //onclick listener for login using user credentials
        login.setOnClickListener {
            isInputEditTextEmail(username,password)
            login(username, password.text.toString())
        }

        //onclick listener for google sign
        googleLogin.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }


    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            Toast.makeText(applicationContext,"Google Login Successful", Toast.LENGTH_SHORT).show()
            var sp = this.getSharedPreferences(AppConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
            var sp1 = sp.edit()
            sp1.putBoolean(AppConstants.SHARED_PREF_KEY,true)
            sp1.commit()

            // Signed in successfully, show authenticated UI.
            //starting HomeActivity
            startActivity(Intent(this, HomeActivity::class.java))


        } catch (e: ApiException) {}
    }

    fun login(username: EditText, password: String) {
        // can be launched in a separate asynchronous job
        if(username!=null && password!=null){

            if((username.text.toString().equals(AppConstants.EMAIL_ID_1) ||
                    username.text.toString().equals(AppConstants.EMAIL_ID_2))
                && password.equals(AppConstants.PASSWORD)){

                val toast = Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                toast.show()
                var sp = this.getSharedPreferences(AppConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
                var sp1 = sp.edit()
                sp1.putBoolean(AppConstants.SHARED_PREF_KEY,true)
                sp1.commit()

                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                username.error = "invalid"

            }
        }

    }


    fun isInputEditTextEmail(textInputEditText: EditText, textInputLayout: EditText): Boolean {
        val emailValue = textInputEditText.toString().trim()
        val passValue = textInputLayout.toString().trim()
        if(emailValue.isEmpty() ){
            textInputLayout.error = ""
            return false
        }
        if(passValue.isEmpty() && passValue.length<8){
            textInputLayout.error = ""
            return false
        }

        return true
    }


}
