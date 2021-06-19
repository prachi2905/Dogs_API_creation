package com.swordhealth.app.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.swordhealth.app.R
import com.swordhealth.app.`interface`.RecyclerItemClickListener
import com.swordhealth.app.adapter.BreedListAdapter
import com.swordhealth.app.model.BreedList
import com.swordhealth.app.databinding.FragmentDogsListBinding
import com.swordhealth.app.utils.Constants
import com.swordhealth.app.view.activity.BreedDetailsActivity


class DogsListFragment : Fragment(), DogListContract.MainView {
    private var selectedBreedItem: String? = null
    private lateinit var binding: FragmentDogsListBinding
    private var presenter: DogListContract.presenter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogsListBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbarAndRecyclerView(view)
        presenter = DogListPresenterImpl(this, GetDogsIntractorImpl())
        (presenter as DogListPresenterImpl).requestDataFromServer()
    }

    /**
     * Initializing Toolbar and RecyclerView
     */
    private fun initializeToolbarAndRecyclerView(view: View) {
        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            (binding.recyclerView.layoutManager as LinearLayoutManager).getOrientation()
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    /**
     * RecyclerItem click event listener
     */
    private val recyclerItemClickListener: RecyclerItemClickListener =
        object : RecyclerItemClickListener {
            override fun onItemClick(breed: BreedList?) {
                 selectedBreedItem = breed?.id
                val intent = Intent(context, BreedDetailsActivity::class.java)
                intent.putExtra(Constants.BREED_NAME, breed?.name)
                intent.putExtra(Constants.BREED_URL, breed?.image?.url)
                intent.putExtra(Constants.ORIGIN, breed?.origin)
                intent.putExtra(Constants.TEMPARAMENT, breed?.temperament)
                startActivity(intent)
            }
        }

    override fun onResponseFailure(throwable: Throwable?) {
        Toast.makeText(
            context,
            getString(R.string.something_went_wrong)+ throwable?.message,
            Toast.LENGTH_LONG
        ).show()

    }

    override fun setDataToRecyclerView(breedArrayList: List<BreedList>) {
        val adapter = BreedListAdapter(breedArrayList, recyclerItemClickListener)
        binding.recyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

}