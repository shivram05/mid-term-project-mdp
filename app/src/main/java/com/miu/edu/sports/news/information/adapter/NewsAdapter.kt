package com.miu.edu.sports.news.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.databinding.ItemsNewsBinding
import com.miu.edu.sports.news.information.model.NewsModel

class NewsAdapter : ListAdapter<NewsModel, NewsAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(
                oldItem: NewsModel,
                newItem: NewsModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: NewsModel,
                newItem: NewsModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(
        private val binding: ItemsNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateView(item: NewsModel) {
            binding.title.text = item.title
            binding.description.text = item.description
            Glide.with(binding.root).load(item.imageUri).apply {
                centerCrop().
                placeholder(R.drawable.miu_logo).
                into(binding.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        return ViewHolder(
            ItemsNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}