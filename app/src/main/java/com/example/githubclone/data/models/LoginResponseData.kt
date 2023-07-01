package com.example.githubclone.data.models

data class LoginResponseData(
    val access_token: String,
    val scope: String,
    val token_type: String
)