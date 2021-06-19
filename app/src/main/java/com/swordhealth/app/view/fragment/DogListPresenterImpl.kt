package com.swordhealth.app.view.fragment

import com.swordhealth.app.model.BreedList

class DogListPresenterImpl(dogListView: DogListContract.MainView?, getBreedIntercetor: DogListContract.GetBreedIntercetor) :
    DogListContract.presenter, DogListContract.GetBreedIntercetor.OnFinishedListener {
    private var dogListView: DogListContract.MainView?
    private val getBreedIntercator: DogListContract.GetBreedIntercetor
    override fun onDestroy() {
        dogListView = null
    }

    override fun onRefreshButtonClick() {
        dogListView?.showProgress()
        getBreedIntercator.getBreedArrayList(this)
        getBreedIntercator.getBreedArrayList(this)
    }

    override fun requestDataFromServer() {
        getBreedIntercator.getBreedArrayList(this)
        getBreedIntercator.getBreedArrayList(this)
    }

    override fun onFinished(breedArrayList: List<BreedList>) {
        if (dogListView != null) {
            dogListView!!.setDataToRecyclerView(breedArrayList)
            dogListView!!.hideProgress()
        }
    }

    override fun onFailure(t: Throwable?) {
        if (dogListView != null) {
            dogListView!!.onResponseFailure(t)
            dogListView!!.hideProgress()
        }
    }

    init {
        this.dogListView = dogListView
        this.getBreedIntercator = getBreedIntercetor
    }
}
