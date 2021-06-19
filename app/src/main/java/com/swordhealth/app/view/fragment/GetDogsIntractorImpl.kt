package com.swordhealth.app.view.fragment

import com.swordhealth.app.api_service.GetDogAPIDataService
import com.swordhealth.app.model.BreedList
import com.swordhealth.app.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetDogsIntractorImpl : DogListContract.GetBreedIntercetor {

    override fun getBreedArrayList(onFinishedListener: DogListContract.GetBreedIntercetor.OnFinishedListener?) {
        /** Create handle for the RetrofitInstance interface */
        val service: GetDogAPIDataService = RetrofitInstance.getRetrofitInstance()?.create(
                GetDogAPIDataService::class.java
        )!!

        /** Call the method with parameter in the interface to get the notice data */
        val call: Call<List<BreedList>> = service.getBreeds()


        call.enqueue(object : Callback<List<BreedList>> {
            override fun onResponse(call: Call<List<BreedList>>, response: Response<List<BreedList>>) {
                response.body()?.let { onFinishedListener?.onFinished(it) }
            }

            override fun onFailure(call: Call<List<BreedList>>, t: Throwable) {
                onFinishedListener?.onFailure(t)
            }
        })

    }
}
