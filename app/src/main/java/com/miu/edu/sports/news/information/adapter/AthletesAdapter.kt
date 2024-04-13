package com.miu.edu.sports.news.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.ItemAthletesBinding
import com.miu.edu.sports.news.information.model.AthletesModel

class AthletesAdapter : ListAdapter<AthletesModel, AthletesAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AthletesModel>() {
            override fun areItemsTheSame(
                oldItem: AthletesModel,
                newItem: AthletesModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AthletesModel,
                newItem: AthletesModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(
        private val binding: ItemAthletesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateView(item: AthletesModel) {

            binding.name.text = binding.root.resources.getString(R.string.name, item.name)
            binding.sport.text = binding.root.resources.getString(R.string.sport, item.competingSport)
            binding.country.text = binding.root.resources.getString(R.string.country, item.origin)
            binding.personalBest.text = binding.root.resources.getString(R.string.personal_bests, item.personalBest)
            binding.medals.text = binding.root.resources.getString(R.string.medals, item.numberOfMedals)
            binding.facts.text = binding.root.resources.getString(R.string.facts, item.interestingFacts)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthletesAdapter.ViewHolder {
        return ViewHolder(
            ItemAthletesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AthletesAdapter.ViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}