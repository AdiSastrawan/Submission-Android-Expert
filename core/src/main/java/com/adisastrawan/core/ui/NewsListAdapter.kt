package com.adisastrawan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adisastrawan.core.R
import com.adisastrawan.core.databinding.ItemNewsLayoutBinding
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.utils.OnAdapterItemClickListener
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: OnAdapterItemClickListener) :
    ListAdapter<News, NewsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(
        private val binding: ItemNewsLayoutBinding,
        private val listener: OnAdapterItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            with(binding) {
                tvTitle.text = news.title
                tvDescription.text = news.description
                Glide.with(itemView.context).load(news.image)
                    .placeholder(R.drawable.baseline_photo_24).into(ivNews)
                itemView.setOnClickListener {
                    listener.onHistoryItemClick(news)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: News,
                newItem: News
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}