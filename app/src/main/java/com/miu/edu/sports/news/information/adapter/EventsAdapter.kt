package com.miu.edu.sports.news.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miu.edu.sports.news.information.databinding.ItemsEventsBinding
import com.miu.edu.sports.news.information.model.EventsModel

class EventsAdapter : ListAdapter<EventsModel, EventsAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<EventsModel>() {
            override fun areItemsTheSame(
                oldItem: EventsModel,
                newItem: EventsModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: EventsModel,
                newItem: EventsModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(
        private val binding: ItemsEventsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateView(item: EventsModel) {
            binding.eventName.text = item.eventName
            binding.description.text = item.description
           binding.date.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        return ViewHolder(
            ItemsEventsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventsAdapter.ViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}