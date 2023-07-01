package com.example.githubclone.data.models

data class SearchRepoByNameResponseData(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)