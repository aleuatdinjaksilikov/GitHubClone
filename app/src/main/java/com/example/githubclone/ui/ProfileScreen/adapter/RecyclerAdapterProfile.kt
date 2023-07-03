package com.example.githubclone.ui.ProfileScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.databinding.RcItemBinding

class RecyclerAdapterProfile():
    ListAdapter<GetUserRepositoriesResponseDataItem, RecyclerAdapterProfile.RecyclerAdapterProfileVH>(diffUtil) {

    inner class RecyclerAdapterProfileVH(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            val d = getItem(position)

            Glide.with(binding.root)
                .load(d.owner.avatar_url)
                .into(binding.miniPhotoUser)

            binding.tvUsername.text = d.owner.login
            binding.tvRepositoryName.text = d.name
            binding.tvProgrammingLanguage.text = d.language
            binding.tvAmountStar.text = d.stargazers_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterProfileVH {
        return RecyclerAdapterProfileVH(
            RcItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerAdapterProfileVH, position: Int) {
        holder.setData(position)
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