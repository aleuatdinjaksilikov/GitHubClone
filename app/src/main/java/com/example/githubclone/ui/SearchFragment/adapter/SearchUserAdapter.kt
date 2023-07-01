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
import com.example.githubclone.data.models.ItemX
import com.example.githubclone.databinding.RcItemRepositoryBinding
import com.example.githubclone.databinding.RcItemRepositorySearchBinding
import com.example.githubclone.databinding.RcItemUserBinding

class SearchUserAdapter:ListAdapter<ItemX, SearchUserAdapter.RepositoryRecyclerVH>(
    diffUtil
) {

    inner class RepositoryRecyclerVH(private val binding:RcItemUserBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val d = getItem(position)

            Glide.with(binding.root)
                .load(d.avatar_url)
                .into(binding.ivAvatar)

            binding.tvLogin.text = d.login

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryRecyclerVH {
        return RepositoryRecyclerVH(
            RcItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerVH, position: Int) {
        holder.setData(position = position)
    }

    private object diffUtil : DiffUtil.ItemCallback<ItemX>() {
        override fun areItemsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem.id == newItem.id
        }

    }
}