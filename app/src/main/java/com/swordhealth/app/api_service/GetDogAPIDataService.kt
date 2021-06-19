package com.swordhealth.app.api_service

import com.swordhealth.app.model.BreedList
import com.swordhealth.app.model.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDogAPIDataService {

    @GET("breeds")
    fun getBreeds(): Call<List<BreedList>>

    //not used because of some param was missing in API call
    @GET("images/search")
    fun getBreedImages(
            @Query("breed_id") breed_id: String,
            @Query("limit") limit: Int,
            @Query("size") size: String?,
            @Query("mime_types") mime_types: Array<String>
    ): Callback<List<Image>>
}