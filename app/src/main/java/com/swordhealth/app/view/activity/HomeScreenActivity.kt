package com.swordhealth.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.swordhealth.app.R
import com.swordhealth.app.databinding.ActivityHomeScreenBinding
import com.swordhealth.app.view.fragment.DogsListFragment
import com.swordhealth.app.view.fragment.SearchBreedFragment

//this activity contains bottom navigation with dog list and search

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setFragment()
    }

    //set the fragment on bottomNavigation View
    fun setFragment() {
        val dogsListFragment = DogsListFragment()
        val searchBreedFragment = SearchBreedFragment()
        setCurrentFragment(dogsListFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dogList -> setCurrentFragment(dogsListFragment)
                R.id.navigation_search -> setCurrentFragment(searchBreedFragment)

            }
            true
        }
    }

    //set the current fragment on bottomNavigation View
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }

}
