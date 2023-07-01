package com.example.githubclone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.Provides

@Entity(tableName = "my_table")
data class HistoryData(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val title:String
)
