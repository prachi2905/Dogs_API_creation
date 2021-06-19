package com.swordhealth.app.view.fragment

import com.swordhealth.app.model.BreedList

interface DogListContract {
    /**
     * Call when user interact with the view and other when view OnDestroy()
     */

    interface presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(breedArrayList: List<BreedList>)
        fun onResponseFailure(throwable: Throwable?)
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     */
     interface GetBreedIntercetor {
        interface OnFinishedListener {
            fun onFinished(breedArrayList: List<BreedList>)
            fun onFailure(t: Throwable?)
        }

        fun getBreedArrayList(onFinishedListener: OnFinishedListener?)
    }
}