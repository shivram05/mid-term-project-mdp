package com.miu.edu.sports.news.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miu.edu.sports.news.information.databinding.ItemSportsBinding
import com.miu.edu.sports.news.information.model.SportsModel

class SportsAdapter : ListAdapter<SportsModel, SportsAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SportsModel>() {
            override fun areItemsTheSame(
                oldItem: SportsModel,
                newItem: SportsModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: SportsModel,
                newItem: SportsModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(
        private val binding: ItemSportsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateView(item: SportsModel) {
            binding.sportsName.text = item.sportsName
            binding.sportsType.text = item.sportsType
            binding.instruction.text = item.instruction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsAdapter.ViewHolder {
        return ViewHolder(
            ItemSportsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsAdapter.ViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}