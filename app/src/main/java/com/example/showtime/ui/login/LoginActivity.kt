package com.example.showtime.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.showtime.Main2Activity
import com.example.showtime.MainActivity
import com.example.showtime.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    val RC_SIGN_IN =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login12)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        var googleLogin = findViewById<SignInButton>(R.id.sign_in_button)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        login.setOnClickListener {
            //  isInputEditTextEmail(username.text,password)
            login(username.text, password.text.toString())
        }

        googleLogin.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent()

            startActivityForResult(signInIntent, RC_SIGN_IN);
        }


    }

    override fun onStart() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        //  updateUI(account)
        super.onStart()
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
            var account = task?.getResult(ApiException::class.java)
            Toast.makeText(applicationContext,"Google Login Successful", Toast.LENGTH_SHORT).show()
            var sp = this.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
            var sp1 = sp.edit()
            sp1.putBoolean("login",true)
            sp1.commit()
            startActivity(Intent(this, Main2Activity::class.java))
            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {}
    }


    fun login(username: Editable, password: String) {
        // can be launched in a separate asynchronous job
        if(username!=null && password!=null){
            if(username.toString().equals("rupali.ranjan1496@gmail.com") && password.equals("rupali@14")){
             //   Toast.makeText(baseContext,"Welcome "+username,)

               // LoginStatus.login=true
                val toast = Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                toast.show()
                var sp = this.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
                var sp1 = sp.edit()
                sp1.putBoolean("login",true)
                sp1.commit()

                startActivity(Intent(this,Main2Activity::class.java))
            }
        }

    }

    fun isInputEditTextEmail(textInputEditText: TextInputEditText, textInputLayout: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.error = "error"
            return false
        } else {
          //  textInputLayout.isErrorEnabled = false
        }
        return true
    }


}
