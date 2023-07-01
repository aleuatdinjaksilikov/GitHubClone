package com.example.githubclone.data.utils

import com.example.githubclone.utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization","Bearer ${SharedPref.pref.getString("access_token","")}"
        ).build()
        return chain.proceed(request)
    }
}