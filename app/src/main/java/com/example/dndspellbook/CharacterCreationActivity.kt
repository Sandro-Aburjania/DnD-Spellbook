package com.example.dndspellbook

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character_creation.*
import kotlinx.android.synthetic.main.activity_character_creation.avatarImageView
import kotlinx.android.synthetic.main.activity_edit_character.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.io.File
import java.util.jar.Manifest

class CharacterCreationActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_creation)
        init()

        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent!!.getChildAt(0) as TextView).textSize = 18f
            }
        }

        levelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent!!.getChildAt(0) as TextView).textSize = 18f
            }
        }

    }

    private fun init() {
        setAvatarButton.setOnClickListener() {

        }
        continueButton.setOnClickListener() {

            val name = sharedPreferences.getString("name", "")
            val description = sharedPreferences.getString("description", "")

            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("description", description)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        sharedPreferences = getSharedPreferences("information", Context.MODE_PRIVATE)
    }

}




