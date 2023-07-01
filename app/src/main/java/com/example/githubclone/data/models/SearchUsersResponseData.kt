package com.example.githubclone.data.models

data class SearchUsersResponseData(
    val incomplete_results: Boolean,
    val items: List<ItemX>,
    val total_count: Int
)