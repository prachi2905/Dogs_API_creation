package com.swordhealth.app.`interface`

import com.swordhealth.app.model.BreedList

interface RecyclerItemClickListener {
    //for navigation of Details Page
    fun onItemClick(breed: BreedList?)
}