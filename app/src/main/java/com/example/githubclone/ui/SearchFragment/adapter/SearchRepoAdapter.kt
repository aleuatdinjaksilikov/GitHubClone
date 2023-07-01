package com.example.githubclone.ui.SearchFragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.data.models.Item
import com.example.githubclone.databinding.RcItemRepositoryBinding
import com.example.githubclone.databinding.RcItemRepositorySearchBinding

class SearchRepoAdapter:ListAdapter<Item, SearchRepoAdapter.RepositoryRecyclerVH>(
    diffUtil
) {

    inner class RepositoryRecyclerVH(private val binding:RcItemRepositorySearchBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val d = getItem(position)

            Glide.with(binding.root)
                .load(d.owner.avatar_url)
                .into(binding.ivAvatar)

            binding.tvUsername.text = d.owner.login
            binding.tvNameRepository.text = d.name
            binding.tvAmountStar.text = d.stargazers_count.toString()
            binding.tvProgrammingLanguage.text = d.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryRecyclerVH {
        return RepositoryRecyclerVH(
            RcItemRepositorySearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerVH, position: Int) {
        holder.setData(position = position)
    }

    private object diffUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

    }
}