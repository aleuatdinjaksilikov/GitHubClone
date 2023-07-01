package com.example.githubclone.ui.SearchFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.databinding.RcItemHistoryBinding

class SearchHistoryAdapter(): ListAdapter<HistoryData, SearchHistoryAdapter.SearchHistoryVH>(
    diffUtil
) {

    private var onClick: ((HistoryData) -> Unit)? = null
    fun onClick(block: (HistoryData) -> Unit) {
        onClick = block
    }

    inner class SearchHistoryVH(private val binding: RcItemHistoryBinding):
        RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val d = getItem(position)
            binding.textHistory.text = d.title
            binding.root.setOnClickListener {
                onClick?.invoke(d)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryVH {
        return SearchHistoryVH(
            RcItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchHistoryVH, position: Int) {
        holder.setData(position = position)
    }

    private object diffUtil : DiffUtil.ItemCallback<HistoryData>() {
        override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
            return oldItem.id == newItem.id
        }

    }
}