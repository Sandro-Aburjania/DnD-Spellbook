package com.example.dndspellbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
    }

    private fun init() {
        auth = Firebase.auth
        signUp.setOnClickListener() {
            signUp()
        }
    }

    private fun signUp() {
        val email: String = emailSignUp.text.toString()
        val password: String = passwordSignUp.text.toString()
        val repeatPassword: String = repeatPasswordSignUp.text.toString()


        if(emailSignUp.text.toString().isEmpty()){
            emailSignUp.error = "Please enter Email"
            emailSignUp.requestFocus()
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailSignUp.text.toString()).matches()){
            emailSignUp.error =  "Please enter valid Email"
            emailSignUp.requestFocus()
        }
        if(passwordSignUp.text.toString().isEmpty()){
            passwordSignUp.error = "Please enter Password"
            passwordSignUp.requestFocus()
        }
        if(repeatPasswordSignUp.text.toString().isEmpty()){
            repeatPasswordSignUp.error = "Please confirm your Password"
            repeatPasswordSignUp.requestFocus()
        }
        if(passwordSignUp.text.toString() != repeatPasswordSignUp.text.toString()){
            repeatPasswordSignUp.error = "Passwords didn't match.Please try again"
            repeatPasswordSignUp.requestFocus()
        }


        if (emailSignUp.text.toString().isNotEmpty() && passwordSignUp.text.toString()
                .isNotEmpty() && repeatPasswordSignUp.text.toString().isNotEmpty()
        ) {
            if (password == repeatPassword) {
                progressbarSignUp.visibility = View.VISIBLE
                deleteClick(true)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        progressbarSignUp.visibility = View.GONE
                        deleteClick(false)
                        if (task.isSuccessful) {
                            Log.d("signUp", "createUserWithEmail:success")
                            Toast.makeText(this,"Sign up is Success!" , Toast.LENGTH_SHORT ).show()
                            val user = auth.currentUser
                            openSignIn()
                        } else {
                            Log.d("signUp", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            }
        }
    }

    private fun deleteClick(isStarted:Boolean){
        signUp.isClickable = !isStarted
    }

    private fun openSignIn(){
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}