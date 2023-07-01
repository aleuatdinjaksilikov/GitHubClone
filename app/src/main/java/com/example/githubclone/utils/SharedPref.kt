package com.example.githubclone.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.githubclone.app.App

class SharedPref {

    companion object{
        val pref: SharedPreferences =
            App.instance.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE)
    }

}