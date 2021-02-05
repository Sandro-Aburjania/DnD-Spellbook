package com.example.dndspellbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        init()
        read()
    }

    private fun init() {
        auth = Firebase.auth
        signInButton.setOnClickListener() {
            signIn()
        }
        signUpButton.setOnClickListener() {
            openSignUp()
        }

        sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    private fun signIn() {
        val email: String = emailSignIn.text.toString()
        val password: String = passwordSignIn.text.toString()

        if (emailSignIn.text.toString().isEmpty()) {
            emailSignIn.error = "Please enter Email"
            emailSignIn.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailSignIn.text.toString()).matches()) {
            emailSignIn.error = "Please enter valid Email"
            emailSignIn.requestFocus()
        }
        if (passwordSignIn.text.toString().isEmpty()) {
            passwordSignIn.error = "Please enter Password"
            passwordSignIn.requestFocus()
        }


        if (emailSignIn.text.toString().isNotEmpty() && passwordSignIn.text.toString()
                .isNotEmpty()
        ) {
            progressbarSignIn.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressbarSignIn.visibility = View.GONE
                    if (task.isSuccessful) {
                        Log.d("logIN", "signInWithEmail:success")
                        Toast.makeText(this, "Authentication is Success!", Toast.LENGTH_SHORT)
                            .show()
                        val user = auth.currentUser
                        openCharacterCreation()
                    } else {
                        Log.d("logIn", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun openCharacterCreation() {
        val intent = Intent(this, CharacterCreationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun openSignUp() {
        val i = Intent(this, SignUpActivity::class.java)
        startActivity(i)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun checkbox(view: View) {
        val email = emailSignIn.text.toString()
        val password = passwordSignIn.text.toString()

        val editor = sharedPreference.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun read() {

        val email = sharedPreference.getString("email", "")
        val password = sharedPreference.getString("password", "")

        emailSignIn.setText(email)
        passwordSignIn.setText(password)


    }


}

