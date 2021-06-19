package com.swordhealth.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.swordhealth.app.`interface`.RecyclerItemClickListener
import com.swordhealth.app.databinding.SearchRowBinding
import com.swordhealth.app.model.BreedList
import java.util.*


class SearchListAdapter(
    dataList: List<BreedList>,
    recyclerItemClickListener: RecyclerItemClickListener,
) : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder?>(), Filterable {

    private lateinit var binding: SearchRowBinding
    private var dataList: List<BreedList>

    var dataFilterList = ArrayList<BreedList>()

    init {
        dataFilterList = dataList as ArrayList<BreedList>
    }

    private val recyclerItemClickListener: RecyclerItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        binding = SearchRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchListViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        binding.breedName.text = dataList.get(position).name

        holder.itemView.setOnClickListener(View.OnClickListener {
            recyclerItemClickListener.onItemClick(
                dataList[position]
            )
        })
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class SearchListViewHolder(binding: SearchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    init {
        this.dataList = dataList as ArrayList<BreedList>
        this.recyclerItemClickListener = recyclerItemClickListener
    }

    //search implemetation

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = dataList as ArrayList<BreedList>
                } else {
                    val resultList = ArrayList<BreedList>()
                    for (row in dataList) {
                        if (row.name?.toLowerCase()
                                ?.contains(constraint.toString().toLowerCase()) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<BreedList>
                notifyDataSetChanged()
            }
        }
    }

    interface BreedAdapterListiner {
        fun on(breedList: BreedList?)
    }
}