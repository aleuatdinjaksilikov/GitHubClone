package com.example.githubclone.ui.RepositoryScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.databinding.RcItemRepositoryBinding

class RepositoryRecyclerAdapter:ListAdapter<GetUserRepositoriesResponseDataItem, RepositoryRecyclerAdapter.RepositoryRecyclerVH>(
    diffUtil
) {

    inner class RepositoryRecyclerVH(private val binding:RcItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val d = getItem(position)

            if (d.private){
                binding.ivLock.visibility = View.VISIBLE
            }

            binding.tvNameRepository.text = d.name
            binding.tvAmountStar.text = d.stargazers_count.toString()
            binding.tvProgrammingLanguage.text = d.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryRecyclerVH {
        return RepositoryRecyclerVH(
            RcItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerVH, position: Int) {
        holder.setData(position = position)
    }

    private object diffUtil : DiffUtil.ItemCallback<GetUserRepositoriesResponseDataItem>() {
        override fun areItemsTheSame(oldItem: GetUserRepositoriesResponseDataItem, newItem: GetUserRepositoriesResponseDataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GetUserRepositoriesResponseDataItem, newItem: GetUserRepositoriesResponseDataItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
}