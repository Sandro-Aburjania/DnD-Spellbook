package com.example.dndspellbook.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dndspellbook.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_spells.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_home, container, false)
        itemView.profileImageButton.setOnClickListener() {
            val intent = Intent(activity, EditCharacter::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        return itemView
    }


}