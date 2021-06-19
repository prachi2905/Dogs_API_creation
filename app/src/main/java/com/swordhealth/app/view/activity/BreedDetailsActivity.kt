package com.swordhealth.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.swordhealth.app.*
import com.swordhealth.app.databinding.ActivityMainBinding
import com.swordhealth.app.utils.Constants

//this activity contains details of breed
class BreedDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        receiveData()

    }

    private fun receiveData() {
        //RECEIVE DATA VIA INTENT
        val intentData = intent
        val breedName = intentData.getStringExtra(Constants.BREED_NAME)
        val breedUrl = intentData.getStringExtra(Constants.BREED_URL)
        val origin = intentData.getStringExtra(Constants.ORIGIN )
        val temparament = intentData.getStringExtra(Constants.TEMPARAMENT)

        binding.breedName.text = getString(R.string.breed_name)+ breedName
        binding.breedOrigin.text = getString(R.string.breed_origin) + origin.toString()
        binding.breedTemparement.text = getString(R.string.breed_temparament)+ temparament.toString()
        binding.exitArrow.setOnClickListener { finish() }
        Picasso.get().load(breedUrl)
                .placeholder(R.drawable.dog_placeholder).into(binding.breedImage)

    }
}