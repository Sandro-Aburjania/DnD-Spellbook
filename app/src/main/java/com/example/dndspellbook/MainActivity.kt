package com.example.dndspellbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dndspellbook.fragments.SpellsFragment
import com.example.dndspellbook.fragments.HomeFragment
import com.example.dndspellbook.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val favouritesFragment = SpellsFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navHome -> makeCurrentFragment(homeFragment)
                R.id.navFavourites -> makeCurrentFragment(favouritesFragment)
                R.id.navSettings -> makeCurrentFragment(settingsFragment)
            }
            true
        }



    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }


}
