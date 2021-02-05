package com.example.dndspellbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashScreenImageView.alpha = 0f
        splashScreenImageView.animate().setDuration(2000).alpha(1f).withEndAction {
            init()

        }


    }

    private fun init() {
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("email", "")

        val password = sharedPreferences.getString("password", "")



        if (email!!.isNotEmpty() && password!!.isNotEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } else {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
