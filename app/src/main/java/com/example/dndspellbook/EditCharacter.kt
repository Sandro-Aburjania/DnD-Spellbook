package com.example.dndspellbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_character.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class EditCharacter : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_character)
        init()
        readInfo()
    }

    private fun init() {
        saveButton.setOnClickListener() {
            openMainActivity()
        }
        logOutButton.setOnClickListener() {
            delete()
            openSignInActivity()
        }


        sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)

        sharedPreferences = getSharedPreferences("information", Context.MODE_PRIVATE)
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun openSignInActivity() {
        val i = Intent(this, SignInActivity::class.java)
        startActivity(i)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun delete() {

        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()

    }

    private fun readInfo() {

        val name = sharedPreferences.getString("name", "")
        val description = sharedPreferences.getString("description", "")

        editCharacterName.setText(name)
        editCharacterDescriptionEditText.setText(description)


    }

}
