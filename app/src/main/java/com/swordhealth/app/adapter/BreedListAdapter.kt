package com.swordhealth.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.swordhealth.app.R
import com.swordhealth.app.`interface`.RecyclerItemClickListener
import com.swordhealth.app.databinding.SingleViewRowBinding
import com.swordhealth.app.model.BreedList
import java.util.*


 class BreedListAdapter(
    dataList: List<BreedList>,
    recyclerItemClickListener: RecyclerItemClickListener
) : RecyclerView.Adapter<BreedListAdapter.BreedViewHolder?>() {

    private lateinit var binding: SingleViewRowBinding
    private val dataList: ArrayList<BreedList>

    private val recyclerItemClickListener: RecyclerItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        binding = SingleViewRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BreedViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        Picasso.get().load(dataList.get(position).image?.url)
            .placeholder(R.drawable.dog_placeholder).into(binding.breedImage)
        holder.itemView.setOnClickListener(View.OnClickListener {
            recyclerItemClickListener.onItemClick(
                dataList[position]
            )
        })
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class BreedViewHolder(binding: SingleViewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    init {
        this.dataList = dataList as ArrayList<BreedList>
        this.recyclerItemClickListener = recyclerItemClickListener
    }
}