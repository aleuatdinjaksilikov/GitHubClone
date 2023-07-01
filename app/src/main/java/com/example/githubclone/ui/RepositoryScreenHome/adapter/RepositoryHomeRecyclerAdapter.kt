package com.example.githubclone.ui.RepositoryScreenHome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.databinding.RcItemRepositoryHomeBinding

class RepositoryHomeRecyclerAdapter:ListAdapter<GetUserRepositoriesResponseDataItem, RepositoryHomeRecyclerAdapter.RepositoryHomeRecyclerVH>(diffUtil) {

    inner class RepositoryHomeRecyclerVH(private val binding:RcItemRepositoryHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(position:Int){
            val d = getItem(position)

            Glide.with(binding.root)
                .load(d.owner.avatar_url)
                .into(binding.ivAvatar)

            binding.tvUsername.text = d.owner.login
            binding.tvNameRepository.text = d.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHomeRecyclerVH {
        return RepositoryHomeRecyclerVH(
            RcItemRepositoryHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryHomeRecyclerVH, position: Int) {
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