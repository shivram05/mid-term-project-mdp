package com.miu.edu.sports.news.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miu.edu.sports.news.information.databinding.ItemsHistoricalArchivesBinding
import com.miu.edu.sports.news.information.model.HistoricalArchivesModel

class HistoricalArchivesAdapter : ListAdapter<HistoricalArchivesModel, HistoricalArchivesAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoricalArchivesModel>() {
            override fun areItemsTheSame(
                oldItem: HistoricalArchivesModel,
                newItem: HistoricalArchivesModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: HistoricalArchivesModel,
                newItem: HistoricalArchivesModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(
        private val binding: ItemsHistoricalArchivesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateView(item: HistoricalArchivesModel) {
            binding.historyName.text = item.historyName
            binding.description.text = item.description
            binding.date.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalArchivesAdapter.ViewHolder {
        return ViewHolder(
            ItemsHistoricalArchivesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoricalArchivesAdapter.ViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}